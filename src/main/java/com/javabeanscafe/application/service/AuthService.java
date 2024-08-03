package com.javabeanscafe.application.service;

import java.util.Base64;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.javabeanscafe.application.exception.CustomException;
import com.javabeanscafe.infrastructure.adapter.authentication.AuthDetails;
import com.javabeanscafe.infrastructure.adapter.authentication.CustomDetailsService;
import com.javabeanscafe.infrastructure.adapter.authentication.JwtService;
import com.javabeanscafe.infrastructure.adapter.authentication.records.AccessToken;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final static String AUTHORIZATION = "Authorization";
    private final static String BASIC = "Basic ";
    private final JwtService jwtService;
    private final CustomDetailsService customDetailsService;
    private final AuthenticationManager authenticationManager;

    public AccessToken login() {
        String[] credentials = getCredentials();

        UserDetails userDetails = customDetailsService.loadUserByUsername(credentials[0]);

        if (Objects.isNull(userDetails)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED,
                    "Credencias incorretas.",
                    "Unauthorized");
        }

        if (!new BCryptPasswordEncoder().matches(credentials[1], userDetails.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED,
                    "Credencias incorretas.",
                    "Unauthorized");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                credentials[0], credentials[1]);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return jwtService.generateToken((AuthDetails) authenticate.getPrincipal());
    }

    private String[] getCredentials() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if (Objects.isNull(servletRequestAttributes)) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "Não foi possivel concluir a requisição.",
                    "Unauthorized");
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();

        if (!existsHeaderAuthorization(request)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED,
                    "Sem autorização",
                    "Unauthorized");
        }

        return base64Decoded(request.getHeader(AUTHORIZATION).replace(BASIC, "")).split(":");
    }

    private String base64Decoded(String request) {
        byte[] decode = Base64.getDecoder().decode(request);

        return new String(decode);
    }

    private boolean existsHeaderAuthorization(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        return Objects.nonNull(header) && header.startsWith(BASIC);
    }

}
