/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.dao;

import br.com.crud.entidade.Academico;
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
public class AcademicoDaoImpl implements Serializable {

    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;

    public void salvar(Academico academico) throws SQLException {
        String sql = "INSERT INTO academico(id, nmAcademico, cpf, dsEmail, dsEndereco, idSexo, idade) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, academico.getId());
            preparando.setString(2, academico.getNome());
            preparando.setString(3, academico.getCpf());
            preparando.setString(4, academico.getEmail());
            preparando.setString(5, academico.getEndereco());
            preparando.setInt(6, academico.getSexo());
            preparando.setInt(7, academico.getIdade());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            academico.setId(resultSet.getInt(1));

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar pessoa " + eSQL.getMessage());
        }
    }

    public void alterar(Academico academico) throws SQLException {
        String sql = "UPDATE academico SET nome = ?, cpf = ?, dsEmail = ?, dsEndereco  = ?, idSexo =?, idade = ? WHERE id = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, academico.getNome());
            preparando.setString(2, academico.getCpf());
            preparando.setString(3, academico.getEmail());
            preparando.setString(4, academico.getEndereco());
            preparando.setInt(5, academico.getSexo());
            preparando.setInt(6, academico.getIdade());
            preparando.setInt(7, academico.getId());
            preparando.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao alterar pessoa " + e.getMessage());

        }
    }

    public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM academico WHERE id = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public Academico pesquisarPorId(Integer id) throws SQLException {
        Academico academico = null;

        String consulta = "SELECT * FROM academico where = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();

            academico = new Academico();
            academico.setId(id);
            academico.setNome(resultSet.getString("nome"));
            academico.setCpf(resultSet.getString("cpf"));
            academico.setEmail(resultSet.getString("dsEmail"));
            academico.setEndereco(resultSet.getString("dsEndereco'"));
            academico.setSexo(resultSet.getInt("idSexo"));
            academico.setId(resultSet.getInt("idade"));
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return academico;
    }
}
