package br.com.equals.xtuff.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_comerciante")
public class Comerciante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_comerciante")
    private Integer id;

    @Column(name = "nome_comerciante", nullable = false)
    private String nome;

    @Column(name = "sobrenome_comerciante", nullable = false)
    private String sobrenome;

    @Column(name = "email_comerciante", nullable = false)
    private String email;

    @Column(name = "senha_comerciante", nullable = false)
    private String senha;

    @Transient
    private String passwordConfirm;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_loja")
    private  Loja loja;

    public Comerciante() {
    }

    public Comerciante(String nome, String sobrenome, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public Integer getId() {
        return id;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
