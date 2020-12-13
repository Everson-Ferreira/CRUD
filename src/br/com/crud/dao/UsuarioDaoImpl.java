/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;


import br.com.crud.entidade.Usuario;
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
public class UsuarioDaoImpl implements Serializable{
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;

    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(cdUsuario, nome, login, senha, logradouro, cidade, estado, cep) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, usuario.getId());
            preparando.setString(2, usuario.getNome());
            preparando.setString(3, usuario.getLogin());
            preparando.setString(4, usuario.getSenha());
            preparando.setString(5, usuario.getLogradoudo());
            preparando.setString(5, usuario.getCidade());
            preparando.setString(5, usuario.getEstado());
            preparando.setString(5, usuario.getCep());
            
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            usuario.setId(resultSet.getInt(1));

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar usuario " + eSQL.getMessage());
        }
    }

    public void alterar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, login = ?, senha = ?, "
                + "logradouro = ?, cidade = ?, estado = ?, cep = ? WHERE cdUsuario = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, usuario.getId());
            preparando.setString(2, usuario.getNome());
            preparando.setString(3, usuario.getLogin());
            preparando.setString(4, usuario.getSenha());
            preparando.setString(5, usuario.getLogradoudo());
            preparando.setString(5, usuario.getCidade());
            preparando.setString(5, usuario.getEstado());
            preparando.setString(5, usuario.getCep());           

            preparando.setInt(1, usuario.getId());
            preparando.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao alterar usuario " + e.getMessage());

        }
    }

    public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM usuarios WHERE cdUsuario = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public Usuario pesquisarPorId(Integer id) throws SQLException {
        Usuario usuario = null;

        String consulta = "SELECT * FROM usuario where = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();

            usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(resultSet.getString("nome"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setLogradoudo(resultSet.getString("logradouro"));
            usuario.setCidade(resultSet.getString("cidade"));
            usuario.setEstado(resultSet.getString("estado"));
            usuario.setCep(resultSet.getString("cep"));
            usuario.setId(resultSet.getInt("cdUsuario"));
            
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return usuario;
    }
    
}
