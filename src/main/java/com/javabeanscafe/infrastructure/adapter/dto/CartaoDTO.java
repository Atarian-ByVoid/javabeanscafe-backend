package com.javabeanscafe.infrastructure.adapter.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO.AbstractDTOViews.TodasInformacoes;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractViews;
import com.javabeanscafe.infrastructure.enums.TipoCartao;

import lombok.Data;

@Data
public class CartaoDTO extends AbstractDTO {

    @JsonView({ CartaoViews.Send.class })
    private String numero;

    @JsonView({ CartaoViews.Send.class })
    private String titular;

    @JsonView({ CartaoViews.Send.class })
    private LocalDate dataValidade;

    @JsonView({ CartaoViews.Send.class })
    private String cvv;

    @JsonView({ CartaoViews.Send.class })
    private TipoCartao tipoCartao;

    @JsonView({ CartaoViews.Send.class })
    private String bandeira;

    private UsuarioDTO usuarioDTO;

    public class CartaoViews extends AbstractViews {
        public interface Send {

        }

        public interface Get extends Send, TodasInformacoes {

        }

    }

}
