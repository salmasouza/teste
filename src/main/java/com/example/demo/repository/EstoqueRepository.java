package com.example.demo.repository;


import com.example.demo.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {


    //Estoque findByProdutoId(Long produtoId);

    List<Estoque> findByProdutoId(Long ProdutoId);


}
