package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository repository;
    public List<Produto> findAll(){
        return repository.findAll();
    }


    public Optional<Produto> findById(Long id){
        return repository.findById(id);
        //return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Produto insert(Produto pessoa){
        return repository.save(pessoa);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    public Produto update(Long id, Produto produto){

        Produto entity = repository.getReferenceById(id);
        updateData(entity, produto);
        return repository.save(entity);

    }
    private void updateData(Produto entity, Produto produto){
        entity.setDescricao(produto.getDescricao());
        entity.setEntrada(produto.getEntrada());
        entity.setData(produto.getData());
        entity.setEntrada(produto.getEntrada());
        entity.setValor(produto.getValor());

    }

    public List<ProdutoDTO> findByDescricao(String descricao){
        return repository.findByDescricao(descricao);
    }


}
