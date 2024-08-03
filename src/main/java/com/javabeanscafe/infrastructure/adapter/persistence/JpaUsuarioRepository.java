package com.javabeanscafe.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.javabeanscafe.application.exception.CustomException;
import com.javabeanscafe.domain.model.Usuario;
import com.javabeanscafe.domain.repository.UsuarioRepository;
import com.javabeanscafe.infrastructure.adapter.persistence.interfaces.UsuarioJpaRepository;

@Repository
public class JpaUsuarioRepository implements UsuarioRepository {
    private final UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    public JpaUsuarioRepository(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioJpaRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(UUID uuid) {
        return usuarioJpaRepository.findById(uuid).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,
                "Usuario não encontrado.", "NOT_FOUND"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return usuarioJpaRepository.loadUserByUsername(email)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,
                        "Usuario não cadastrado na plataforma.", "NOT_FOUND"));
    }

    @Override
    public Optional<Usuario> findByCpfAndEmail(String cpf, String email) {
        Optional<Usuario> usuarioExistente = usuarioJpaRepository.findByCpfAndEmail(cpf, email);
        if (usuarioExistente.isPresent()) {
            throw new CustomException(HttpStatus.FOUND, "Usuário já cadastrado", "Found");
        }
        return usuarioExistente;
    }

    @Override
    public Usuario findById(UUID requestedUserId) {

        Usuario usuario = usuarioJpaRepository.findById(requestedUserId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,
                        "Usuario não encontrado na plataforma.", "NOT_FOUND"));

        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
    }

    @Override
    public void atualizar(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void excluir(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

}