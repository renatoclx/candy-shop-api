package com.crud.candyshopapi.domain.produto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "produtos")
@Entity(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private float precoCusto;
    private float precoVenda;
    private Integer ativo;

    public Produto(RequestProduto requestProduto) {
        this.nome = requestProduto.nome();
        this.precoCusto = requestProduto.precoCusto();
        this.precoVenda = requestProduto.precoVenda();
        this.ativo = 1;
    }
}
