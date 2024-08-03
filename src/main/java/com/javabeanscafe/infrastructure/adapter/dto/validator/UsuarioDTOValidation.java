package com.javabeanscafe.infrastructure.adapter.dto.validator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javabeanscafe.infrastructure.enums.Genero;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTOValidation {

    @NotBlank(message = "O primeiro nome é obrigatório", groups = UsuarioValidation.Create.class)
    private String nome;

    @NotBlank(message = "O e-email é obrigatório", groups = UsuarioValidation.Create.class)
    @Email(message = "O e-mail está no fomrato inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória", groups = UsuarioValidation.Create.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "Telefone é obrigatório", groups = UsuarioValidation.Create.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String telefone;

    @NotBlank(message = "CPF é obrigatório", groups = UsuarioValidation.Create.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpf;

    @NotNull(message = "Genero é obrigatório", groups = UsuarioValidation.Create.class)
    private Genero genero;

    private EnderecoDTOValidation endereco = new EnderecoDTOValidation();;

    public class UsuarioValidation {

        public interface Create {

        }

    }

}
