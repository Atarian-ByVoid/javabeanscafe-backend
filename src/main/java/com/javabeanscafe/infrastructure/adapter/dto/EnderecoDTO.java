package com.javabeanscafe.infrastructure.adapter.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO;

import lombok.Data;

@Data
public class EnderecoDTO extends AbstractDTO {

    @JsonView({ EnderecoViews.Send.class })
    private String logradouro;

    @JsonView({ EnderecoViews.Send.class })
    private String bairro;

    @JsonView({ EnderecoViews.Send.class })
    private String cidade;

    @JsonView({ EnderecoViews.Send.class })
    private String uf;

    @JsonView({ EnderecoViews.Send.class })
    private String pais;

    @JsonView({ EnderecoViews.Send.class })
    private String cep;

    @JsonView({ EnderecoViews.Send.class })
    private String complemento;

    @JsonView({ EnderecoViews.Send.class })
    private String numero;

    public class EnderecoViews {
        public interface Send {

        }

        public interface Detalhado extends Send {

        }
    }

}
