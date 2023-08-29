package com.example.demo.controller;

import com.example.demo.controller.errors.BadRequestAlertException;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.dto.ComparativoView;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.dto.ResultadoDataView;
import com.example.demo.service.dto.ResultadoNomeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api")
public class ProdutoController {

    private final Logger log = LoggerFactory.getLogger(ProdutoController.class);
    private static final String ENTITY_NAME = "produtos";
    private final ProdutoService produtoService;

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoService produtoService,ProdutoRepository produtoRepository){

        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;

    }

    //BUSCAR TODOS OS PRODUTOS
    @GetMapping(value = "/produtos")
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos(Pageable pageable) {
        log.debug("REST request to get a page of Produto");
        Page<ProdutoDTO> page = produtoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }

    //BUSCA DE PRODUTO COMPARATIVO- ENTRADA E SAIDA
    @GetMapping(value = "/produtos/comparativo")
    public ResponseEntity<List<ComparativoView>> getComparativo(@RequestParam(required = false) Integer mes,
                                                                @RequestParam(required = false) Integer dia,@RequestParam(required = false)  Integer ano,Pageable pageable) {
        log.debug("REST request to get a page of Produto");
        Page<ComparativoView> page = produtoService.findByTodos(mes, dia, ano, pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }
//CADASTRO DE PRODUTO
    @PostMapping(value = "/produto")
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDTO) throws URISyntaxException {
        log.debug("REST request to save Produto : {}", produtoDTO);

        if (produtoDTO.getId() != null) {
            throw new BadRequestAlertException("A new Produto cannot already have an ID", ENTITY_NAME, "idexists");
        }

        ProdutoDTO result = produtoService.save(produtoDTO);
        return ResponseEntity
                .created(new URI("/api/rh/produto" + result.getId()))
                .body(result);
    }
    //DELETAR PRODUTO
    @DeleteMapping(value = "/produto/{produtoId}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long produtoId) {
        log.debug("REST request to delete Produto : {}", produtoId);
        produtoService.delete(produtoId);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert("apiProduto", false, ENTITY_NAME, produtoId.toString()))
                .build();
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable Long id) {
        log.debug("REST request to get Produto : {}", id);
        Optional<ProdutoDTO> produtoDTO = produtoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produtoDTO);
    }

    //ALTERAÇÃO DE PRODUTO
    @PatchMapping(value = "/produto/{produtoId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProdutoDTO> partialUpdateProduto(
            @PathVariable(value = "produtoId", required = false) final Long produtoId,
            @RequestBody ProdutoDTO produtoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Estoque partially : {}, {}", produtoId, produtoDTO);

        if (produtoDTO.getId() == null) throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        if (!Objects.equals(produtoId, produtoDTO.getId())) throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        if (!produtoRepository.existsById(produtoId)) throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");

        Optional<ProdutoDTO> result = produtoService.partialUpdate(produtoDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert("apiProduto", false, ENTITY_NAME, produtoDTO.getId().toString())
        );
    }


    //BUSCA DE PRODUTO PELO NOME
    @GetMapping("/produtos/nome/{descricao}")
    public ResponseEntity<List<ResultadoNomeView>> getProdutobyDescricao(@PathVariable String descricao, Pageable pageable) {
        log.debug("REST request to get Produto id: {}", descricao);
        Page<ResultadoNomeView> page = produtoService.findByDescricao(descricao, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }

    ////BUSCA DE PRODUTO(SAIDA E ENTRADA) POR INTERVALO DE DATA
    @GetMapping("/produtos/data/nome/{descricao}")
    public ResponseEntity<List<ResultadoDataView>> getProdutobyData(@PathVariable String descricao, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim, Pageable pageable) {
        log.debug("REST request to get Produto: {}", descricao, dataInicio, dataFim);
        Page<ResultadoDataView> page = produtoService.findByData(descricao,dataInicio,dataFim, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }



}













