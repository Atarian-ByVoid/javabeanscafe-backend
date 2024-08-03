package com.javabeanscafe.domain.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javabeanscafe.domain.model.Endereco;
import com.javabeanscafe.domain.model.Pedido;
import com.javabeanscafe.infrastructure.adapter.dto.CafeDTO;
import com.javabeanscafe.infrastructure.adapter.dto.EnderecoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.ItemPedidoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.PedidoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.UsuarioDTO;

@Component
public class PedidoMapper {

    public PedidoDTO convertToPedidoDTO(Pedido pedido, BigDecimal precoTotal) {
        PedidoDTO pedidoDTO = new PedidoDTO();

        List<ItemPedidoDTO> itemPedidoDTOList = pedido.getItens().stream()
                .map(itemPedido -> {
                    ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                    itemPedidoDTO.setCriadoEm(itemPedido.getCriadoEm());
                    itemPedidoDTO.setAlteradoEm(itemPedido.getAlteradoEm());
                    itemPedidoDTO.setDeletadoEm(itemPedido.getDeletadoEm());
                    itemPedidoDTO.setId(itemPedido.getId());
                    CafeDTO cafeDTO = new CafeDTO();
                    cafeDTO.setCriadoEm(itemPedido.getCafe().getCriadoEm());
                    cafeDTO.setAlteradoEm((itemPedido.getCafe().getAlteradoEm()));
                    cafeDTO.setDeletadoEm(itemPedido.getCafe().getDeletadoEm());
                    cafeDTO.setId((itemPedido.getCafe().getId()));
                    cafeDTO.setNome((itemPedido.getCafe().getNome()));
                    cafeDTO.setTipoCafe(itemPedido.getCafe().getTipoCafe());
                    cafeDTO.setValor(itemPedido.getCafe().getValor());
                    cafeDTO.setTamanhoCafe(itemPedido.getCafe().getTamanhoCafe());
                    cafeDTO.setDescricao(itemPedido.getCafe().getDescricao());
                    itemPedidoDTO.setCafe(cafeDTO);
                    itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());

                    return itemPedidoDTO;
                }).collect(Collectors.toList());

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCriadoEm(pedido.getUsuario().getCriadoEm());
        usuarioDTO.setDeletadoEm(pedido.getUsuario().getDeletadoEm());
        usuarioDTO.setAlteradoEm(pedido.getUsuario().getAlteradoEm());
        usuarioDTO.setId(pedido.getId());
        usuarioDTO.setEmail(pedido.getUsuario().getEmail());
        usuarioDTO.setCpf(pedido.getUsuario().getCpf());
        usuarioDTO.setGenero(pedido.getUsuario().getGenero());
        usuarioDTO.setNome(pedido.getUsuario().getNome());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        Endereco endereco = pedido.getUsuario().getEndereco();
        enderecoDTO.setCriadoEm(endereco.getCriadoEm());
        enderecoDTO.setAlteradoEm(endereco.getAlteradoEm());
        enderecoDTO.setDeletadoEm(endereco.getDeletadoEm());
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setPais(endereco.getPais());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setUf(endereco.getUf());
        usuarioDTO.setEnderecoDTO(enderecoDTO);

        pedidoDTO.setCriadoEm(pedido.getCriadoEm());
        pedidoDTO.setAlteradoEm(pedido.getAlteradoEm());
        pedidoDTO.setDeletadoEm(pedido.getDeletadoEm());
        pedidoDTO.setItemPedido(itemPedidoDTOList);
        pedidoDTO.setValorDaTransacao(precoTotal);
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setUsuario(usuarioDTO);

        return pedidoDTO;
    }

}
