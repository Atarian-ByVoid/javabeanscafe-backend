package com.javabeanscafe.infrastructure.adapter.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.javabeanscafe.application.exception.CustomException;
import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.domain.repository.CafeRepository;
import com.javabeanscafe.infrastructure.adapter.persistence.interfaces.CafeJpaRepository;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

@Repository
public class JpaCafeRepository implements CafeRepository {

    private final CafeJpaRepository cafeJpaRepository;

    @Autowired
    public JpaCafeRepository(CafeJpaRepository cafeJpaRepository) {
        this.cafeJpaRepository = cafeJpaRepository;
    }

    @Override
    public void saveAll(List<Cafe> cafes) {
        this.cafeJpaRepository.saveAll(cafes);
    }

    @Override
    public Cafe findById(UUID uuid) {
        return this.cafeJpaRepository.findById(uuid).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,
                "Café não encontradado na plataforma.", "NOT_FOUND"));
    }

    @Override
    public Page<Cafe> findAllCafes(Pageable pageable) {

        return this.cafeJpaRepository.findAll(pageable);

    }

    @Override
    public List<Cafe> findAllById(List<UUID> ids) {
        return this.cafeJpaRepository.findAllById(ids);
    }

    @Override
    public List<Cafe> findCafeByEnum(TipoCafe tipoCafe) {

        return this.cafeJpaRepository.findCafeByEnum(tipoCafe)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,
                        "Tipo de café não encontrado.", "NOT_FOUND"));
    }

}
