package com.javabeanscafe.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.javabeanscafe.domain.model.Usuario;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Usuario buscarPorId(UUID uuid);

    List<Usuario> buscarTodos();

    void atualizar(Usuario usuario);

    void excluir(Usuario usuario);

    UserDetails loadUserByUsername(String email);

    Optional<Usuario> findByCpfAndEmail(String cpf, String email);

    Usuario findById(UUID requestedUserId);
}
