package com.example.demo.controller;


import com.example.demo.controller.errors.BadRequestAlertException;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.service.EstoqueService;
import com.example.demo.service.dto.EstoqueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class EstoqueController {
    private final Logger log = LoggerFactory.getLogger(EstoqueController.class);

    private static final String ENTITY_NAME = "estoque";

    private final EstoqueService estoqueService;

    private final EstoqueRepository estoqueRepository;

    public EstoqueController(EstoqueService estoqueService,  EstoqueRepository estoqueRepository) {
        this.estoqueService = estoqueService;
        this.estoqueRepository = estoqueRepository;
    }
    @GetMapping("/estoque/produtos/{produtoId}")
    public ResponseEntity<List<EstoqueDTO>> getAllEstoqueProdutos(@PathVariable Long produtoId) {
        List<EstoqueDTO> listEstoqueDTO = estoqueService.findEstoqueByProdututoID(produtoId);
        return ResponseEntity.ok().body(listEstoqueDTO);
    }

    // CADASTRO DE ENTRADA E SAIDA DE ESTOQUE
    @PostMapping("/estoque")
    public ResponseEntity<EstoqueDTO> createEstoque(@RequestBody EstoqueDTO estoqueDTO) throws URISyntaxException {
        log.debug("REST request to save estoque : {}", estoqueDTO);

        if (estoqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new estoqueDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }

        EstoqueDTO result = estoqueService.save(estoqueDTO);
        return ResponseEntity
                .created(new URI("/api/rh/estoque" + result.getId()))
                .body(result);
    }
    @DeleteMapping("/estoque/{idEstoque}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long idEstoque) {
        log.debug("REST request to delete Estoque : {}", idEstoque);
        estoqueService.delete(idEstoque);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert("apiConfeccao", false, ENTITY_NAME, idEstoque.toString()))
                .build();
    }

    //ATUALIZAR ESTORNO DE SAIDA E ENTRADA
    @PatchMapping(value = "/estoque/{idEstoque}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EstoqueDTO> partialUpdateEstoque(
            @PathVariable(value = "idEstoque", required = false) final Long idEstoque,
            @RequestBody EstoqueDTO estoqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Estoque partially : {}, {}", estoqueDTO, estoqueDTO);

        if (estoqueDTO.getId() == null) throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        if (!Objects.equals(idEstoque, estoqueDTO.getId())) throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        if (!estoqueRepository.existsById(idEstoque)) throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");

        Optional<EstoqueDTO> result = estoqueService.partialUpdate(estoqueDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert("apiConfeccao", false, ENTITY_NAME, estoqueDTO.getId().toString())
        );
    }
}

