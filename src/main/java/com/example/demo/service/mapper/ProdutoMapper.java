package com.example.demo.service.mapper;

import com.example.demo.model.Produto;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.mapper.EntityMapper;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface ProdutoMapper extends EntityMapper<ProdutoDTO, Produto> {

}