package com.example.demo.service;


import com.example.demo.model.Estoque;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.dto.EstoqueDTO;
import com.example.demo.service.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface EstoqueService {


    EstoqueDTO save(EstoqueDTO estoqueDTO);
    EstoqueDTO update(EstoqueDTO estoqueDTO);
    Optional<EstoqueDTO> partialUpdate(EstoqueDTO estoqueDTO);
    Page<EstoqueDTO> findAll(Pageable pageable);
    Optional<EstoqueDTO> findOne(Long id);

    List<EstoqueDTO> findEstoqueByProdututoID(Long idProdutos);
    void delete(Long idEstoque);





}
