package com.crud.candyshopapi.controllers;

import com.crud.candyshopapi.domain.produto.Produto;
import com.crud.candyshopapi.domain.produto.ProdutoRepository;
import com.crud.candyshopapi.domain.produto.RequestProduto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity getAllProdutos() {
        var allProducts = repository.findAllByAtivoTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduto(@RequestBody @Valid RequestProduto data) {

        Produto newProduto = new Produto(data);
        repository.save(newProduto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduto(@PathVariable Integer id, @RequestBody @Valid RequestProduto data) {
        Optional<Produto> produtoOpcional = repository.findById(id);

        if(produtoOpcional.isPresent()) {
            Produto produto = produtoOpcional.get();
            produto.setNome(data.nome());
            produto.setPrecoCusto(data.precoCusto());
            produto.setPrecoVenda(data.precoVenda());

            return ResponseEntity.ok(produto);
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduto(@PathVariable Integer id) {
        Optional<Produto> produtoOpcional = repository.findById(id);

        if(produtoOpcional.isPresent()) {
            Produto produto = produtoOpcional.get();
            produto.setAtivo(0);

            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
