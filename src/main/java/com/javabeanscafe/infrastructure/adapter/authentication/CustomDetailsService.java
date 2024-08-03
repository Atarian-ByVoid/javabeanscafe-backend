package com.javabeanscafe.infrastructure.adapter.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javabeanscafe.domain.repository.UsuarioRepository;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.loadUserByUsername(email);
    }

}
