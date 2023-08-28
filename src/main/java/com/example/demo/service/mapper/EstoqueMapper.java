package com.example.demo.service.mapper;

import com.example.demo.model.Estoque;
import com.example.demo.model.Produto;
import com.example.demo.service.dto.EstoqueDTO;
import com.example.demo.service.dto.ProdutoDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EstoqueMapper extends EntityMapper <EstoqueDTO, Estoque>{

    @Mapping(target = "produto", source = "produto", qualifiedByName = "produtoId")
    EstoqueDTO toDto(Estoque s);

    @Named("produtoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProdutoDTO toDtoProdutoId(Produto Produto);
}
