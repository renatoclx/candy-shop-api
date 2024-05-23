package com.crud.candyshopapi.domain.venda;

import com.crud.candyshopapi.infra.VendaResumoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
    @Query(value = "SELECT v.id AS venda_id, v.cliente_id AS cliente_id, c.nome AS nome, SUM(i.preco_total_produto) AS total " +
            "FROM venda_item i " +
            "JOIN venda v ON i.venda_id = v.id " +
            "JOIN clientes c ON v.cliente_id = c.id " +
            "AND v.ativo = 1 " +
            "GROUP BY v.id, c.id",
            nativeQuery = true)
    List<Object[]> findVendaResumo();
}
