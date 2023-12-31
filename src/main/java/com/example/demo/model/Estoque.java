package com.example.demo.model;

import com.example.demo.model.enumeration.Estorno;
import com.example.demo.model.enumeration.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "public", name="tbl_estoque")
public class Estoque implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "estoque_quantidade")
    private Integer quantidade;
    @Column(name = "estoque_situacao")
    private Situacao situacao;

    @Column(name = "estoque_estorno")
    private Integer estorno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "estoque"}, allowSetters = true)
    private Produto produto;

    public Estoque(){}

    public Estoque(Long id, Integer quantidade, Situacao situacao,Estorno estorno, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.situacao =situacao;
        this.produto = produto;
        setEstorno(estorno);
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

    public void adicionarProdutos(Integer quantidade) {
        this.quantidade += quantidade;
    }
    public void removerProdutos(Integer quantidade) {
        this.quantidade -= quantidade;
    }


    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estorno getEstorno() {
        return Estorno.valueOf(estorno);
    }

    public void setEstorno(Estorno estorno) {
        if(estorno != null){
            this.estorno = estorno.getCodigo();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", situacao=" + situacao +
                ", estorno=" + estorno +
                ", produto=" + produto +
                '}';
    }
}
