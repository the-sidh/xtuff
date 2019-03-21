package br.com.equals.xtuff.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_loja")
    private Integer id;

    @Column(name = "nome_loja", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;
//
//    @OneToMany( cascade=CascadeType.ALL)
//    @JoinColumn(name="id_produto")
//    private Collection<Produto> produtos = new ArrayList<Produto>();

    public Loja(Integer id, String nome, String email, String senha, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        //this.produtos = produtos;
    }

    public Loja(Integer id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Loja() {
        super();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public Collection<Produto> getProdutos() {
//        return produtos;
//    }
//
//    public void setProdutos(Collection<Produto> produtos) {
//        this.produtos = produtos;
//    }
}
