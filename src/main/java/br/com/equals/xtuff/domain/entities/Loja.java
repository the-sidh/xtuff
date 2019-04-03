package br.com.equals.xtuff.domain.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_store")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_loja")
    private Integer id;

    @Column(name = "nome_loja", nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user = new User();


    @OneToOne
    @JoinColumn(name = "id_end")
    private Endereco endereco = new Endereco();

    @OneToMany(mappedBy = "loja")
    private Collection<Produto> produtos = new ArrayList<Produto>();

    public Loja(Integer id, String nome, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;

        this.produtos = produtos;
    }

    public Loja(Integer id, String nome, User user) {
        this.id = id;
        this.nome = nome;
        this.user = user;
    }

    public Loja(Integer id, String nome) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Collection<Produto> produtos) {
        this.produtos = produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
