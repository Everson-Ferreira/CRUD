/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.entidade;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Usuario implements Serializable{
    
    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private String logradoudo;
    private String cidade;
    private String estado;
    private String cep;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String login, String senha, String logradoudo, String cidade, String estado, String cep) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.logradoudo = logradoudo;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradoudo() {
        return logradoudo;
    }

    public void setLogradoudo(String logradoudo) {
        this.logradoudo = logradoudo;
    }
    
   
}
