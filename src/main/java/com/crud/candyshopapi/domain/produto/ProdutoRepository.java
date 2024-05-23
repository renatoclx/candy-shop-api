package com.crud.candyshopapi.domain.produto;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findAllByAtivoTrue();

    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
