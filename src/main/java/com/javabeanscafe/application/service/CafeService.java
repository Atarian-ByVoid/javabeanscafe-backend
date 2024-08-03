package com.javabeanscafe.application.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javabeanscafe.domain.helper.PageWrapper;
import com.javabeanscafe.domain.helper.PaginateHelper;
import com.javabeanscafe.domain.mapper.CafeMapper;
import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.domain.repository.CafeRepository;
import com.javabeanscafe.infrastructure.adapter.dto.CafeDTO;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    private final CafeMapper cafeMapper;

    public CafeDTO findById(String id, HttpServletRequest token) {

        UUID cafeId = UUID.fromString(id);

        Cafe cafe = cafeRepository.findById(cafeId);

        return this.cafeMapper.convertToDto(cafe);
    }

    public Cafe findByIdEntidade(String id, HttpServletRequest token) {

        UUID cafeId = UUID.fromString(id);

        Cafe cafe = cafeRepository.findById(cafeId);

        return cafe;

    }

    public List<Cafe> buscarCafesPorIds(List<String> idsCafe) {
        List<UUID> ids = idsCafe.stream().map(UUID::fromString).collect(Collectors.toList());
        return cafeRepository.findAllById(ids);
    }

    public PageWrapper<CafeDTO> findAllCafes(
            int page,
            int size,
            List<String> sortProperties,
            List<String> sortOrders,
            HttpServletRequest token) {
        page = PaginateHelper.validarPage(page, null);
        size = PaginateHelper.validarSize(size, null, null);
        Sort sort = PaginateHelper.buildSort(sortProperties, sortOrders);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Cafe> cafesPage = cafeRepository.findAllCafes(pageable);

        List<CafeDTO> cafesDTO = cafesPage.getContent().stream()
                .map(cafe -> {
                    CafeDTO cafeDTO = new CafeDTO();
                    cafeDTO.setCriadoEm(cafe.getCriadoEm());
                    cafeDTO.setAlteradoEm(cafe.getAlteradoEm());
                    cafeDTO.setDeletadoEm(cafe.getDeletadoEm());
                    cafeDTO.setDescricao(cafe.getDescricao());
                    cafeDTO.setId(cafe.getId());
                    cafeDTO.setNome(cafe.getNome());
                    cafeDTO.setTamanhoCafe(cafe.getTamanhoCafe());
                    cafeDTO.setTipoCafe(cafe.getTipoCafe());
                    cafeDTO.setValor(cafe.getValor());
                    return cafeDTO;

                })
                .toList();

        Page<CafeDTO> t = new PageImpl<>(
                cafesDTO, pageable,
                cafesPage.getTotalElements());

        return new PageWrapper<CafeDTO>().build(t);

    }

    public List<CafeDTO> buscarCafesPorEnum(
            TipoCafe tipoCafe,
            HttpServletRequest token) {

        List<Cafe> cafe = this.cafeRepository.findCafeByEnum(tipoCafe);

        return this.cafeMapper.toDTOList(cafe);

    }

}
