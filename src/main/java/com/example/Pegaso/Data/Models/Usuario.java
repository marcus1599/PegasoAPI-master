package com.example.Pegaso.Data.Models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String biografia;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Comentario> comentarios;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Postagem> postagens;

    @Column(nullable = false)
    @JsonIgnore // Ignora o campo senha nas respostas JSON por segurança
    private String senha;


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUserName() {
        return username;
    }

    public void addComentario(Comentario comentario) {

        setComentario(comentarios);
        this.comentarios.add(comentario);
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public List<Comentario> getComentario() {
        return this.comentarios;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentarios = comentario;
    }

    
    @JsonIgnore
    public List<Postagem> getPostagems() {
        return this.postagens;
    }

    public void setPostagem(List<Postagem> postagems) {
        this.postagens = postagems;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

}