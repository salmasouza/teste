package com.example.demo.repository;


import com.example.demo.model.Produto;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.dto.ResultadoNomeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where upper(p.descricao)like concat(upper(?1),'%')")
    List<ResultadoNomeView> findByDescricao(String descricao);


}
