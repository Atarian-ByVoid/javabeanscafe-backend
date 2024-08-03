package com.javabeanscafe.infrastructure.adapter.dto.validator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTOValidation {

    @NotBlank(message = "Logradouro é obrigatório", groups = EnderecoValidation.Create.class)
    private String logradouro;

    @NotBlank(message = "bairro é obrigatório", groups = EnderecoValidation.Create.class)
    private String bairro;

    @NotBlank(message = "cidade é obrigatório", groups = EnderecoValidation.Create.class)
    private String cidade;

    @NotBlank(message = "uf é obrigatório", groups = EnderecoValidation.Create.class)
    private String uf;

    @NotBlank(message = "pais é obrigatório", groups = EnderecoValidation.Create.class)
    private String pais;

    @NotBlank(message = "cep é obrigatório", groups = EnderecoValidation.Create.class)
    private String cep;

    @NotBlank(message = "Numero é obrigatório", groups = EnderecoValidation.Create.class)
    private String numero;

    @NotBlank(message = "complemento é obrigatório", groups = EnderecoValidation.Create.class)
    private String complemento;

    public class EnderecoValidation {

        public interface Create {

        }
    }

}
