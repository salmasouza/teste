package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.dto.ResultadoDataView;
import com.example.demo.service.dto.ResultadoNomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface ProdutoService {


    ProdutoDTO save(ProdutoDTO produtoDTO);
    ProdutoDTO update(ProdutoDTO produtoDTO);
    Optional<ProdutoDTO> partialUpdate(ProdutoDTO produtoDTO);
    Page<ProdutoDTO> findAll(Pageable pageable);
    Optional<ProdutoDTO> findOne(Long id);
    void delete(Long idProduto);

    Page<ResultadoNomeView> findByDescricao(String descricao, Pageable pageable);

    Page<ResultadoDataView> findByData(String descricao, LocalDate dataInicio, LocalDate dataFim, Pageable pageable);




}
