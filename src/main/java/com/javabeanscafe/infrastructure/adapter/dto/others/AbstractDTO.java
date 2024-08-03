package com.javabeanscafe.infrastructure.adapter.dto.others;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public abstract class AbstractDTO {

    @JsonView({ AbstractViews.ComDataCriacao.class, AbstractDTOViews.TodasInformacoes.class })
    private LocalDateTime criadoEm;

    @JsonView({ AbstractViews.ComDataAlteracao.class, AbstractDTOViews.TodasInformacoes.class })
    private LocalDateTime alteradoEm;

    @JsonView({ AbstractViews.ComDataExclusao.class, AbstractDTOViews.TodasInformacoes.class })
    private LocalDateTime deletadoEm;

    @JsonView({ AbstractViews.ComIdentificador.class, AbstractDTOViews.TodasInformacoes.class })
    private UUID id;

    public class AbstractDTOViews {
        public interface TodasInformacoes {

        }

    }

}
