package com.crud.candyshopapi.controllers;

import com.crud.candyshopapi.domain.venda.Venda;
import com.crud.candyshopapi.infra.VendaDTO;
import com.crud.candyshopapi.service.VendaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.candyshopapi.infra.VendaResumoDTO;

import java.util.List;


@RestController
@RequestMapping("/venda")
@CrossOrigin
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    @Transactional
    public ResponseEntity<Venda> criarVenda(@Valid @RequestBody VendaDTO vendaDTO) {

        Venda venda = vendaService.criarVenda(vendaDTO);

        return ResponseEntity.ok(venda);
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<VendaResumoDTO>> getVendaResumo() {
        List<VendaResumoDTO> resumo = vendaService.buscarVendaResumo();
        return ResponseEntity.ok(resumo);
    }
}
