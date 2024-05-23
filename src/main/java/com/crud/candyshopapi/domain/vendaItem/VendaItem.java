package com.crud.candyshopapi.domain.vendaItem;

import com.crud.candyshopapi.domain.produto.Produto;
import com.crud.candyshopapi.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "venda_item")
@Entity(name = "venda_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class VendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidadeProduto;
    private double precoUnitarioProduto;
    private double precoTotalProduto;
}
