/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;

import br.com.crud.entidade.Autores;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class AutoresDaoImpl implements Serializable {

    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;

    public void salvar(Autores autores) throws SQLException {
        String sql = "INSERT INTO autores(cdAutores, nmAutor, cdLivro) "
                + "VALUES(?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, autores.getIdAutor());
            preparando.setString(2, autores.getNome());
            preparando.setInt(3, autores.getIdLivro());
            
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            autores.setIdAutor(resultSet.getInt(2));

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar pessoa " + eSQL.getMessage());
        }
    }

    public void alterar(Autores autores) throws SQLException {
        String sql = "UPDATE autores SET nmAutor = ?, cdLivro = ? WHERE cdAutores = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, autores.getNome());
            preparando.setInt(2, autores.getIdLivro());
            preparando.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao alterar pessoa " + e.getMessage());

        }
    }

    public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM academico WHERE cdAutores = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public Autores pesquisarPorId(Integer id) throws SQLException {
        Autores autor = null;

        String consulta = "SELECT * FROM academico where = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();

            autor = new Autores();
            autor.setIdAutor(id);
            autor.setNome(resultSet.getString("nome"));
            autor.setIdLivro(resultSet.getInt("cdLivro"));
            
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return autor;
    }
}
