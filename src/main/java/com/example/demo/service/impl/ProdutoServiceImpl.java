package com.example.demo.service.impl;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.EstoqueService;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.dto.EstoqueDTO;
import com.example.demo.service.dto.ProdutoDTO;
import com.example.demo.service.dto.ResultadoDataView;
import com.example.demo.service.dto.ResultadoNomeView;
import com.example.demo.service.mapper.ProdutoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    private final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    private final ProdutoMapper produtoMapper;

    private final ProdutoRepository produtoRepository;

    private final EstoqueService estoqueService;

    public ProdutoServiceImpl(ProdutoMapper produtoMapper,ProdutoRepository produtoRepository,EstoqueService estoqueService){
        this.produtoMapper = produtoMapper;
        this.produtoRepository = produtoRepository;
        this.estoqueService =estoqueService;
    }
    @Override
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        log.debug("Request to save Produto : {}", produtoDTO);
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDto(produto);
    }

    @Override
    public ProdutoDTO update(ProdutoDTO produtoDTO) {
        log.debug("Request to update Produto : {}", produtoDTO);
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDto(produto);
    }

    @Override
    public Optional<ProdutoDTO> partialUpdate(ProdutoDTO produtoDTO) {
        log.debug("Request to partially update Produto : {}", produtoDTO);
        return produtoRepository
                .findById(produtoDTO.getId())
                .map(existingProduto -> {
                    produtoMapper.partialUpdate(existingProduto, produtoDTO);
                    return existingProduto;
                })
                .map(produtoRepository::save)
                .map(produtoMapper::toDto);
    }

    @Override
    public Page<ProdutoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Produto");
        return produtoRepository.findAll(pageable).map(produtoMapper::toDto);
    }

    @Override
    public Optional<ProdutoDTO> findOne(Long id) {
        log.debug("request to get Produto with id : {}", id);
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(produtoMapper::toDto);
    }

    @Override
    public void delete(Long produtoId) {
        List<EstoqueDTO> estoqueDTOList = estoqueService.findEstoqueByProdututoID(produtoId);
        if (estoqueDTOList.isEmpty()){
            produtoRepository.deleteById(produtoId);
        } else {
            estoqueDTOList.stream().forEach(estoqueDTO -> {
                estoqueService.delete(estoqueDTO.getId());
            });
            produtoRepository.deleteById(produtoId);
        }

    }

    @Override
    public Page<ResultadoNomeView> findByDescricao(String descricao, Pageable pageable) {
        Page<ResultadoNomeView> resultado = produtoRepository.findByDescricao(descricao,pageable);
        return resultado;
    }

    public Page<ResultadoDataView> findByData(String descricao, LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        Page<ResultadoDataView> resultado = produtoRepository.findByData(descricao,dataInicio,dataFim,pageable);
        return resultado;
    }


}









