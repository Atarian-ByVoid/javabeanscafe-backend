package com.javabeanscafe.infrastructure.adapter.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO.AbstractDTOViews.TodasInformacoes;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractViews.Paginate;
import com.javabeanscafe.infrastructure.enums.TamanhoCafe;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

import lombok.Data;

@Data
public class CafeDTO extends AbstractDTO {

    @JsonView({ CafeViews.Get.class })
    private String descricao;

    @JsonView({ CafeViews.Get.class })
    private String nome;

    @JsonView({ CafeViews.Get.class })
    private TamanhoCafe tamanhoCafe;

    @JsonView({ CafeViews.Get.class })
    private TipoCafe tipoCafe;

    @JsonView({ CafeViews.Get.class })
    private BigDecimal valor;

    public class CafeViews {
        public interface Get extends Paginate, TodasInformacoes {

        }

    }

}
