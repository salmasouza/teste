package com.example.demo.service.impl;

import com.example.demo.model.Estoque;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.service.EstoqueService;
import com.example.demo.service.dto.EstoqueDTO;
import com.example.demo.service.mapper.EstoqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EstoqueServiceImpl {

    private final Logger log = LoggerFactory.getLogger(EstoqueService.class);

    private final EstoqueMapper estoqueMapper;

    private final EstoqueRepository estoqueRepository;

    private final EstoqueService estoqueService;

    public EstoqueServiceImpl(EstoqueMapper estoqueMapper,EstoqueRepository estoqueRepository,EstoqueService estoqueService) {
        this.estoqueMapper = estoqueMapper;
        this.estoqueRepository = estoqueRepository;
        this.estoqueService=estoqueService;
    }


    public EstoqueDTO save(EstoqueDTO estoqueDTO) {
        log.debug("Request to save Estoque : {}", estoqueDTO);
        Estoque estoque = estoqueMapper.toEntity(estoqueDTO);
        estoque = estoqueRepository.save(estoque);
        return estoqueMapper.toDto(estoque);
    }


    public EstoqueDTO update(EstoqueDTO estoqueDTO) {
        log.debug("Request to update Estoque : {}", estoqueDTO);
        Estoque estoque = estoqueMapper.toEntity(estoqueDTO);
        estoque = estoqueRepository.save(estoque);
        return estoqueMapper.toDto(estoque);
    }


    public Optional<EstoqueDTO> partialUpdate(EstoqueDTO estoqueDTO) {
        log.debug("Request to partially update Estoque : {}", estoqueDTO);
        return estoqueRepository
                .findById(estoqueDTO.getId())
                .map(existingEstoque -> {
                    estoqueMapper.partialUpdate(existingEstoque, estoqueDTO);
                    return existingEstoque;
                })
                .map(estoqueRepository::save)
                .map(estoqueMapper::toDto);
    }


    public Page<EstoqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Estoque");
        return estoqueRepository.findAll(pageable).map(estoqueMapper::toDto);
    }


    public Optional<EstoqueDTO> findOne(Long id) {
        log.debug("request to get Estoque with id : {}", id);
        Optional<Estoque> estoque = estoqueRepository.findById(id);
        return estoque.map(estoqueMapper::toDto);
    }


    public List<EstoqueDTO> findByEstoqueByProdututoId(Long idProdutos) {
        List<Estoque> listEstoqueProdutoId = estoqueRepository.findByEstoqueId(idProdutos);

        List<EstoqueDTO> listEstoqueProdutoDTO = listEstoqueProdutoId.stream().map(estoque -> estoqueMapper.toDto(estoque)).collect(Collectors.toList());
        return listEstoqueProdutoDTO;
    }


    public void delete(Long idEstoque) {
        List<EstoqueDTO> feriasTransferenciaDTOList = estoqueService.findEstoqueByProdututoID(idEstoque);
        if (feriasTransferenciaDTOList.isEmpty()){
            estoqueRepository.deleteById(idEstoque);
        } else {
            feriasTransferenciaDTOList.stream().forEach(estoqueDTO -> {
                estoqueService.delete(estoqueDTO.getId());
            });
            estoqueRepository.deleteById(idEstoque);
        }
    }

}
