package br.com.equals.xtuff.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "t_product")
public class Produto  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome_produto", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_insercao")
    private Calendar dataInsercao;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_validade")
    private Calendar dataValidade;

    @Column(name = "qtd", nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_loja")
    private Loja loja;

    public Produto() {
        super();
    }

    public Produto(Integer id, String nome, Double preco, Calendar dataInsercao, Calendar dataValidade, Integer quantidade, Loja loja) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.dataInsercao = dataInsercao;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.loja = loja;
    }

    public Produto(Integer id, String nome, Double preco, Calendar dataInsercao, Calendar dataValidade, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.dataInsercao = dataInsercao;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
    }

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
    @JsonIgnore
    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }
}
