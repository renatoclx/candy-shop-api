package com.crud.candyshopapi.service;

import com.crud.candyshopapi.domain.cliente.Cliente;
import com.crud.candyshopapi.domain.cliente.ClienteRepository;
import com.crud.candyshopapi.domain.produto.Produto;
import com.crud.candyshopapi.domain.produto.ProdutoRepository;
import com.crud.candyshopapi.domain.venda.Venda;
import com.crud.candyshopapi.domain.venda.VendaRepository;
import com.crud.candyshopapi.domain.vendaItem.VendaItem;
import com.crud.candyshopapi.infra.ProdutoDTO;
import com.crud.candyshopapi.infra.VendaDTO;
import com.crud.candyshopapi.infra.VendaResumoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    public VendaService(){}

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public List<VendaResumoDTO> buscarVendaResumo() {
        List<Object[]> results = vendaRepository.findVendaResumo();
        return results.stream()
                .map(result -> new VendaResumoDTO(
                        ((Number) result[0]).longValue(),
                        ((Number) result[1]).longValue(),
                        (String) result[2],
                        ((Number) result[3]).doubleValue()))
                .collect(Collectors.toList());
    }
    @Transactional
    public Venda criarVenda(VendaDTO vendaDTO) {

        Venda venda = new Venda();
        Cliente cliente = clienteRepository.findById(vendaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        List<ProdutoDTO> produtoDTOs = vendaDTO.getItens();
        if (produtoDTOs == null || produtoDTOs.isEmpty()) {
            throw new RuntimeException("A lista de produtos não pode ser nula ou vazia");
        }

        List<VendaItem> itens = new ArrayList<>();
        for(ProdutoDTO produtoDTO : vendaDTO.getItens()) {
            Produto produto = produtoRepository.findById(produtoDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

            VendaItem vendaItem = new VendaItem();
            vendaItem.setProduto(produto);
            vendaItem.setQuantidadeProduto(produtoDTO.getQuantidadeProduto());
            vendaItem.setPrecoUnitarioProduto(produtoDTO.getPrecoUnitarioProduto());
            vendaItem.setPrecoTotalProduto(produtoDTO.getPrecoTotalProduto());
            vendaItem.setVenda(venda);
            itens.add(vendaItem);
        }

        venda.setCliente(cliente);
        venda.setItens(itens);
        venda.setAtivo(1);

        for (VendaItem item : itens) {
            item.setVenda(venda);
        }

        return vendaRepository.save(venda);
    }
}
