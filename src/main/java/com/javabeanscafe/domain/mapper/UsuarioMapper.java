package com.javabeanscafe.domain.mapper;

import org.springframework.stereotype.Component;

import com.javabeanscafe.domain.model.Endereco;
import com.javabeanscafe.domain.model.Usuario;
import com.javabeanscafe.infrastructure.adapter.dto.EnderecoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.UsuarioDTO;

@Component
public class UsuarioMapper {

    public UsuarioDTO convertToUsuarioDTO(Usuario usuario, Endereco endereco) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCriadoEm(usuario.getAlteradoEm());
        usuarioDTO.setDeletadoEm(usuario.getDeletadoEm());
        usuarioDTO.setAlteradoEm(usuario.getAlteradoEm());
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getPassword());
        usuarioDTO.setGenero(usuario.getGenero());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCriadoEm(endereco.getAlteradoEm());
        enderecoDTO.setDeletadoEm(endereco.getDeletadoEm());
        enderecoDTO.setAlteradoEm(endereco.getAlteradoEm());
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setPais(endereco.getPais());
        enderecoDTO.setUf(endereco.getUf());
        enderecoDTO.setNumero(endereco.getNumero());
        usuarioDTO.setEnderecoDTO(enderecoDTO);

        return usuarioDTO;
    }
}
