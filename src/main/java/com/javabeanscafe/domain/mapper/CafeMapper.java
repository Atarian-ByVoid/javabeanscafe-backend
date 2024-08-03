package com.javabeanscafe.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.infrastructure.adapter.dto.CafeDTO;

@Component
public class CafeMapper {

    public CafeDTO convertToDto(Cafe cafe) {
        CafeDTO cafeDto = new CafeDTO();
        cafeDto.setCriadoEm(cafe.getCriadoEm());
        cafeDto.setDeletadoEm(cafe.getDeletadoEm());
        cafeDto.setAlteradoEm(cafe.getAlteradoEm());
        cafeDto.setId(cafe.getId());
        cafeDto.setNome(cafe.getNome());
        cafeDto.setDescricao(cafe.getDescricao());
        cafeDto.setValor(cafe.getValor());
        cafeDto.setTamanhoCafe(cafe.getTamanhoCafe());
        cafeDto.setTipoCafe(cafe.getTipoCafe());

        return cafeDto;
    }

    public List<CafeDTO> toDTOList(List<Cafe> cafes) {
        return cafes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
