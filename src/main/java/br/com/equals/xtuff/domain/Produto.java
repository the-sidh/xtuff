package br.com.equals.xtuff.domain;

import java.util.Calendar;

public class Produto {

    private Integer id;
    private String nome;
    private Double preco;
    private Calendar dataInsercao;
    private Calendar dataValidade;
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Calendar getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(Calendar dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public Calendar getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Calendar dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
