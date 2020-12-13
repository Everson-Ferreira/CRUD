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
public class Emprestimo implements Serializable{
    private Integer idEmprestimo;
    private Integer idUsuario;
    private Integer idBibliotecario;
    private Integer idLivro;
    private String dataDevolucao;
    private String dataRetirada;

    public Emprestimo() {
    }

    public Emprestimo(Integer idEmprestimo, Integer idUsuario, Integer idBibliotecario, Integer idLivro, String dataDevolucao, String dataRetirada) {
        this.idEmprestimo = idEmprestimo;
        this.idUsuario = idUsuario;
        this.idBibliotecario = idBibliotecario;
        this.idLivro = idLivro;
        this.dataDevolucao = dataDevolucao;
        this.dataRetirada = dataRetirada;
    }

    

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(Integer idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
    
}