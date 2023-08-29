package com.example.demo.service.dto;


import com.example.demo.model.enumeration.Estorno;
import com.example.demo.model.enumeration.Situacao;

import java.io.Serializable;
import java.util.Objects;

public class EstoqueDTO implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer quantidade;

    private Situacao situacao;

    private Estorno estorno;

    private ProdutoDTO produto;

    public EstoqueDTO() {
    }

    public EstoqueDTO(Long id, Integer quantidade, Situacao situacao,Estorno estorno, ProdutoDTO produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.situacao = situacao;
        this.produto = produto;
        this.estorno = estorno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Estorno getEstorno() {
        return estorno;
    }

    public void setEstorno(Estorno estorno) {
        this.estorno = estorno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueDTO that = (EstoqueDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EstoqueDTO{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", situacao=" + situacao +
                ", estorno=" + estorno +
                ", produto=" + produto +
                '}';
    }
}
