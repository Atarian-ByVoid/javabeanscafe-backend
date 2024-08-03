package com.javabeanscafe.infrastructure.adapter.persistence.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

public interface CafeJpaRepository extends JpaRepository<Cafe, UUID> {

    @Query("SELECT c FROM Cafe c WHERE c.tipoCafe = :tipoCafe")
    Optional<List<Cafe>> findCafeByEnum(@Param("tipoCafe") TipoCafe tipoCafe);
}
