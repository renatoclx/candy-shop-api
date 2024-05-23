package com.crud.candyshopapi.domain.venda;

import com.crud.candyshopapi.domain.cliente.Cliente;
import com.crud.candyshopapi.domain.vendaItem.VendaItem;
import com.crud.candyshopapi.infra.VendaResumoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Table(name = "venda")
@Entity(name = "venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SqlResultSetMapping(
        name = "VendaResumoDTO",
        classes = @ConstructorResult(
                targetClass = VendaResumoDTO.class,
                columns = {
                        @ColumnResult(name = "venda_id", type = Integer.class),
                        @ColumnResult(name = "cliente_id", type = Integer.class),
                        @ColumnResult(name = "nome", type = String.class),
                        @ColumnResult(name = "preco_total_produto", type = Double.class)
                }
        )
)

public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Cliente cliente;

    private Integer ativo;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaItem> itens;

}
