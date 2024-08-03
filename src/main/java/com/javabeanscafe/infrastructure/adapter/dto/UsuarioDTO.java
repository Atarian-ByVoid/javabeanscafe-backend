package com.javabeanscafe.infrastructure.adapter.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO;
import com.javabeanscafe.infrastructure.enums.Genero;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UsuarioDTO extends AbstractDTO {

    @JsonView({ UsuarioViews.Send.class, UsuarioViews.ParaPedido.class })
    private String nome;

    @JsonView({ UsuarioViews.Send.class, UsuarioViews.ParaPedido.class })
    private String email;

    @JsonView({ UsuarioViews.Send.class, UsuarioViews.ParaPedido.class })
    private String cpf;

    @JsonView({ UsuarioViews.Send.class })
    private String senha;

    @JsonView({ UsuarioViews.Send.class, UsuarioViews.ParaPedido.class })
    private Genero genero;

    @JsonView({ UsuarioViews.Send.class, UsuarioViews.ParaPedido.class })
    private EnderecoDTO enderecoDTO;

    public class UsuarioViews {
        public interface Send {

        }

        public interface Detalhado extends Send {

        }

        public interface ParaPedido {

        }
    }

}
