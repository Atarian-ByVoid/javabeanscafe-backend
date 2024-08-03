package com.javabeanscafe.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

public interface CafeRepository {

    void saveAll(List<Cafe> cafes);

    Cafe findById(UUID uuid);

    Page<Cafe> findAllCafes(Pageable pageable);

    List<Cafe> findAllById(List<UUID> ids);

    List<Cafe> findCafeByEnum(TipoCafe tipoCafe);

}
