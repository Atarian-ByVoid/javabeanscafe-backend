package com.javabeanscafe.infrastructure.adapter.entrypoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.application.service.CafeService;
import com.javabeanscafe.domain.helper.PageWrapper;
import com.javabeanscafe.domain.helper.PaginateHelper;
import com.javabeanscafe.infrastructure.adapter.dto.CafeDTO;
import com.javabeanscafe.infrastructure.adapter.dto.CafeDTO.CafeViews;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "cafe")
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/{idCafe}")
    @JsonView(CafeViews.Get.class)
    public ResponseEntity<CafeDTO> buscarCafe(
            @PathVariable(required = true) String idCafe,
            HttpServletRequest token

    ) {

        return ResponseEntity.ok(cafeService.findById(idCafe, token));
    }

    @GetMapping()
    @JsonView(CafeViews.Get.class)
    public ResponseEntity<PageWrapper<CafeDTO>> buscarTodosCafes(
            @RequestParam(defaultValue = PaginateHelper.PAGE_MIN_REQUEST) int page,
            @RequestParam(defaultValue = PaginateHelper.SIZE_MIN_REQUEST) int size,
            @RequestParam(required = false) List<String> sortProperties,
            @RequestParam(required = false) List<String> sortOrders,
            HttpServletRequest token

    ) {

        return ResponseEntity.ok(cafeService.findAllCafes(page,
                size,
                sortProperties,
                sortOrders,
                token));
    }

    @GetMapping("/enum")
    @JsonView(CafeViews.Get.class)
    public ResponseEntity<List<CafeDTO>> buscarCafe(
            @RequestParam(required = false) TipoCafe tipoCafe,
            HttpServletRequest token

    ) {

        return ResponseEntity.ok(cafeService.buscarCafesPorEnum(tipoCafe, token));
    }

}
