package br.com.equals.xtuff.domain.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_store")
public class Loja  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_loja")
    private Integer id;

    @Column(name = "nome_loja", nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_end")
    private Endereco endereco = new Endereco();

    @OneToMany(mappedBy = "loja",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Produto> produtos = new HashSet<Produto>();

    public Loja(Integer id, String nome, Set<Produto> produtos) {
        this.id = id;
        this.nome = nome;

        this.produtos = produtos;
    }

    public Loja(Integer id, String nome, Endereco endereco, Set<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.produtos = produtos;
    }

    public Loja(String nome) {
        this.nome = nome;
    }

    public Loja() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
