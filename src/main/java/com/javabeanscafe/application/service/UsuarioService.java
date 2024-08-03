package com.javabeanscafe.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.javabeanscafe.application.exception.CustomException;
import com.javabeanscafe.domain.mapper.UsuarioMapper;
import com.javabeanscafe.domain.model.Endereco;
import com.javabeanscafe.domain.model.Usuario;
import com.javabeanscafe.domain.repository.EnderecoRepository;
import com.javabeanscafe.domain.repository.UsuarioRepository;
import com.javabeanscafe.infrastructure.adapter.authentication.JwtService;
import com.javabeanscafe.infrastructure.adapter.dto.UsuarioDTO;
import com.javabeanscafe.infrastructure.adapter.dto.validator.UsuarioDTOValidation;
import com.javabeanscafe.infrastructure.mail.MailService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Service
@Data
public class UsuarioService {

    @Autowired
    private JwtService jwtService;

    private final UsuarioRepository usuarioRepository;

    private final EnderecoRepository enderecoRepository;

    private final UsuarioMapper usuarioMapper;

    private final MailService mailService;

    public final UsuarioDTO save(UsuarioDTOValidation request) {

        Optional<Usuario> usuarioExistente = usuarioRepository.findByCpfAndEmail(request.getCpf(), request.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new CustomException(HttpStatus.FOUND, "Usuário já cadastrado",
                    "Found");
        }

        Endereco endereco = new Endereco();
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCep(request.getEndereco().getCep());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setComplemento(request.getEndereco().getComplemento());
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setPais(request.getEndereco().getPais());
        endereco.setUf(request.getEndereco().getUf());
        endereco.setNumero(request.getEndereco().getNumero());

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getSenha());
        usuario.setTelefone(request.getTelefone());
        usuario.setGenero(request.getGenero());
        usuario.setCpf(request.getCpf());
        usuario.encryptPassword();
        usuario.setEndereco(endereco);

        Usuario usuarioSalvo = usuarioRepository.salvar(usuario);

        this.mailService.sendEmailBoasVindas(usuarioSalvo);
        return usuarioMapper.convertToUsuarioDTO(usuarioSalvo, endereco);
    }

    public UsuarioDTO findById(String uuid, HttpServletRequest token) {

        String authToken = jwtService.extractToken(token);

        String userId = jwtService.getUserIdFromToken(authToken);
        UUID accountId = UUID.fromString(userId);

        UUID requestedUserId = UUID.fromString(uuid);
        Usuario usuario = usuarioRepository.findById(requestedUserId);

        if (!usuario.getId().equals(accountId)) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                    "Você não tem permissão para acessar esse recurso.", "Forbidden");
        }

        return usuarioMapper.convertToUsuarioDTO(usuario, usuario.getEndereco());
    }

}
