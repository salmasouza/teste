package com.example.demo.service.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class ProdutoDTO implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 1L;


    private Long id;

    private String descricao;

    private LocalDate data;

    private Double valor;

    private Integer entrada;

    public ProdutoDTO(){};

    public ProdutoDTO(Long id, String descricao, LocalDate data, Double valor, Integer entrada) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.entrada = entrada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getEntrada() {
        return entrada;
    }

    public void setEntrada(Integer entrada) {
        this.entrada = entrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDTO that = (ProdutoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", valor=" + valor +
                ", entrada=" + entrada +
                '}';
    }
}