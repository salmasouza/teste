package com.example.demo.service;


import com.example.demo.service.dto.EstoqueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface EstoqueService {


    EstoqueDTO save(EstoqueDTO estoqueDTO);
    EstoqueDTO update(EstoqueDTO estoqueDTO);
    Optional<EstoqueDTO> partialUpdate(EstoqueDTO estoqueDTO);

    Page<EstoqueDTO> findAll(Pageable pageable);
    Optional<EstoqueDTO> findOne(Long id);

    List<EstoqueDTO> findEstoqueByProdututoID(Long produtoId);
    void delete(Long idEstoque);




}
