package com.crud.candyshopapi.infra;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VendaDTO {
    private int clienteId;
    private List<ProdutoDTO> itens = new ArrayList<>();
}
