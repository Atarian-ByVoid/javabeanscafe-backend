package com.javabeanscafe.infrastructure.adapter.entrypoint;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javabeanscafe.application.service.UsuarioService;
import com.javabeanscafe.infrastructure.adapter.dto.UsuarioDTO;
import com.javabeanscafe.infrastructure.adapter.dto.validator.UsuarioDTOValidation;
import com.javabeanscafe.infrastructure.adapter.dto.validator.UsuarioDTOValidation.UsuarioValidation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(
            @Validated({ UsuarioValidation.Create.class }) @RequestBody UsuarioDTOValidation request) {

        UsuarioDTO usuarioDTO = usuarioService.save(request);

        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping()
    public ResponseEntity<UsuarioDTO> buscarUsuario(
            @RequestParam(required = true) String uuid,
            HttpServletRequest token) {

        UsuarioDTO usuarioDTO = usuarioService.findById(uuid, token);

        return ResponseEntity.ok(usuarioDTO);
    }

}
