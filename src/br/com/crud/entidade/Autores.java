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
public class Autores implements Serializable{
    private Integer idAutor;
    private Integer idLivro;
    private String nome;

    public Autores() {
    }

    public Autores(Integer idAutor, Integer idLivro, String nome) {
        this.idAutor = idAutor;
        this.idLivro = idLivro;
        this.nome = nome;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
}
