package com.crud.candyshopapi.service;

import com.crud.candyshopapi.domain.produto.Produto;
import com.crud.candyshopapi.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findProdutos(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
