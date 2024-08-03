package com.javabeanscafe.infrastructure.adapter.persistence.interfaces;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.javabeanscafe.domain.model.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, UUID> {

    @Query("""
            SELECT NEW com.javabeanscafe.infrastructure.adapter.authentication.AuthDetails(
            u.id, u.disabled, u.email, u.password
            )
            FROM Usuario u
            WHERE LOWER(u.email) = LOWER(:email)
            """)
    Optional<UserDetails> loadUserByUsername(@Param("email") String email);

    Optional<Usuario> findByCpfAndEmail(String cpf, String email);

}