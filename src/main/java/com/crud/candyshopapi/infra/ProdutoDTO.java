package com.crud.candyshopapi.infra;

import lombok.*;

@Getter
@Setter
public class ProdutoDTO {

    private int id;
    private int quantidadeProduto;
    private double precoUnitarioProduto;
    private double precoTotalProduto;
}
