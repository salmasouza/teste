package com.example.demo.repository;


import com.example.demo.model.Produto;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.dto.ResultadoDataView;
import com.example.demo.service.dto.ResultadoNomeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//    @Query("select p, et.quantidade, et.situacao from Produto p left join Estoque et on et.produto_id= p.id where upper(p.descricao)like concat(upper(?1),'%')")
//    List<ResultadoNomeView> findByDescricao(String descricao);

    @Query(value = "select p.produto_descricao, et.estoque_quantidade, et.estoque_situacao from tbl_confeccao p\n" +
            "left join tbl_estoque et on et.produto_id= p.id where upper(p.produto_descricao)like concat(upper(?1),'%')", nativeQuery = true)
    Page<ResultadoNomeView> findByDescricao(String descricao, Pageable pageable);

    @Query(value = "select p.produto_descricao, et.estoque_quantidade, et.estoque_situacao from tbl_confeccao p\n" +
            "left join tbl_estoque et on et.produto_id= p.id where upper(p.produto_descricao)like concat(upper(?1),'%') and p.produto_data between ?2 and ?3", nativeQuery = true)
    Page<ResultadoDataView> findByData(String descricao, LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
}
