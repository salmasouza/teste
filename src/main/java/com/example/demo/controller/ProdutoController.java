package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.dto.ResultadoNomeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value ="/api")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/produtos")
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/produto/{id}")
    public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id) {
        Optional<Produto> produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/produto")
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        produto = service.insert(produto);
        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping(value = "/produto/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        produto = service.update(id, produto);
        return ResponseEntity.ok().body(produto);

    }

    @GetMapping(value = "/produto/nome/{descricao}")
    public ResponseEntity<List<ResultadoNomeView>> findByDescricao(@PathVariable String descricao) {
        List<ResultadoNomeView> produto = service.findByDescricao(descricao);
        return ResponseEntity.ok().body(produto);
    }


}





