/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;


import br.com.crud.entidade.Livros;
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
public class LivrosDaoImpl implements Serializable{
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Livros livros) throws SQLException {
        String sql = "INSERT INTO livros(cdLivro, titulo, resumo, dtPublicacao, edicao, nmEditora, cidade, estado) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, livros.getId());
            preparando.setString(2, livros.getTitulo());
            preparando.setString(3, livros.getResumo());
            preparando.setString(4, livros.getDataPublicacao());
            preparando.setString(5, livros.getEdicao());
            preparando.setString(6, livros.getEditora());
            preparando.setString(7, livros.getCidade());
            preparando.setString(8, livros.getEstado());

            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            livros.setId(resultSet.getInt(1));

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar livro " + eSQL.getMessage());
        }
    }

    public void alterar(Livros livros) throws SQLException {
        String sql = "UPDATE livros SET titulo = ?, resumo = ?, dtPublicacao = ?, "
                + "edicao = ?, nmEditora = ?, cidade = ?, estado = ? WHERE cdLivro = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, livros.getId());
            preparando.setString(2, livros.getTitulo());
            preparando.setString(3, livros.getResumo());
            preparando.setString(4, livros.getDataPublicacao());
            preparando.setString(5, livros.getEdicao());
            preparando.setString(6, livros.getEditora());
            preparando.setString(7, livros.getCidade());
            preparando.setString(8, livros.getEstado());
            preparando.setInt(1, livros.getId());
            preparando.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao alterar livro " + e.getMessage());

        }
    }

    public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM livros WHERE cdLivro = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public Livros pesquisarPorId(Integer id) throws SQLException {
        Livros livros = null;

        String consulta = "SELECT * FROM academico where cdBibliotecario = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();

            livros = new Livros();
            livros.setId(id);
            livros.setResumo(resultSet.getString("resumo"));
            livros.setDataPublicacao(resultSet.getString("dtPublicacao"));
            livros.setEdicao(resultSet.getString("edicao"));
            livros.setEditora(resultSet.getString("nmEditora"));
            livros.setCidade(resultSet.getString("cidade"));
            livros.setEstado(resultSet.getString("estado"));
            
            

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return livros;
    }
    
}
