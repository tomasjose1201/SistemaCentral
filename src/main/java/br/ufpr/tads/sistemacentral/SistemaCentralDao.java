/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.sistemacentral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class SistemaCentralDao {

    private final String stmtFindUser = "select * from treinador where login = ? and senha = ?";
    private final String stmtInsert = "insert into pokemon values (null, ?, ?, ?, ?, ?, ?)";
    private final String stmtSelectAll = "select * from pokemon";
    private final String stmtSelectById = "select * from pokemon a where idPokemon = ?";
    private final String stmtSelectNomeTreinador = "select nomeTreinador from treinador where idTreinador = ?";
    private final String stmtSelectPokemonFavorito = "select b.* from pokemon_favorito a, pokemon b where a.idTreinador = ? and a.idPokemon = b.idPokemon";
    private final String stmtSelectPokemonByNome = "select * from pokemon where nomePokemon like ? limit 1";
    private Connection con;

    public SistemaCentralDao() {
    }

    public Treinador findUser(Treinador treinador) throws SQLException {
        con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            Treinador tr = null;
            stmt = con.prepareStatement(stmtFindUser);
            stmt.setString(1, treinador.getLogin());
            stmt.setString(2, treinador.getSenha());
            rs = stmt.executeQuery();
            while (rs.next()) {
                tr = new Treinador();
                tr.setIdTreinador(rs.getInt("idTreinador"));
                tr.setNomeTreinador(rs.getString("nomeTreinador"));
                tr.setLogin(rs.getString("login"));
                tr.setSenha(rs.getString("senha"));
            }
            return tr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }

    public void insert(Pokemon pokemon) throws SQLException {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(stmtInsert);
            stmt.setString(1, pokemon.getNomePokemon());
            stmt.setString(2, pokemon.getEspecie());
            stmt.setDouble(3, pokemon.getPeso());
            stmt.setDouble(4, pokemon.getAltura());
            stmt.setInt(5, pokemon.getIdTreinador());
            stmt.setString(6, pokemon.getFoto());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }

    public ArrayList<Pokemon> selectAll() throws SQLException {
        con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        ArrayList<Pokemon> lista = new ArrayList();
        try {
            Pokemon poke = null;
            stmt = con.prepareStatement(stmtSelectAll);
            rs = stmt.executeQuery();
            while (rs.next()) {
                poke = new Pokemon();
                poke.setIdPokemon(rs.getInt("idPokemon"));
                poke.setNomePokemon(rs.getString("nomePokemon"));
                poke.setEspecie(rs.getString("especie"));
                poke.setPeso(rs.getDouble("peso"));
                poke.setAltura(rs.getDouble("altura"));
                poke.setIdTreinador(rs.getInt("idTreinador"));
                poke.setFoto("foto");
                lista.add(poke);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }

    public Pokemon selectById(int id) throws SQLException {
        con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            Pokemon poke = null;
            stmt = con.prepareStatement(stmtSelectById);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                poke = new Pokemon();
                poke.setIdPokemon(rs.getInt("idPokemon"));
                poke.setNomePokemon(rs.getString("nomePokemon"));
                poke.setEspecie(rs.getString("especie"));
                poke.setPeso(rs.getDouble("peso"));
                poke.setAltura(rs.getDouble("altura"));
                poke.setIdTreinador(rs.getInt("idTreinador"));
                poke.setFoto(rs.getString("foto"));
            }
            return poke;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }

    public String selectNomeTreinador(int id) throws SQLException {
        con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String nome = "";
            stmt = con.prepareStatement(stmtSelectNomeTreinador);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                nome = rs.getString("nomeTreinador");
            }
            return nome;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }

    public Pokemon selectPokemonFavorito(int id) throws SQLException {
        con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            Pokemon poke = null;
            stmt = con.prepareStatement(stmtSelectPokemonFavorito);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                poke = new Pokemon();
                poke.setIdPokemon(rs.getInt("idPokemon"));
                poke.setNomePokemon(rs.getString("nomePokemon"));
                poke.setEspecie(rs.getString("especie"));
                poke.setPeso(rs.getDouble("peso"));
                poke.setAltura(rs.getDouble("altura"));
                poke.setIdTreinador(rs.getInt("idTreinador"));
                poke.setFoto(rs.getString("foto"));
            }
            return poke;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }

    public Pokemon selectPokemonByNome(String nomePokemon) throws SQLException {
        con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            Pokemon poke = null;
            stmt = con.prepareStatement(stmtSelectPokemonByNome);
            stmt.setString(1, '%' + nomePokemon + '%');
            rs = stmt.executeQuery();
            while (rs.next()) {
                poke = new Pokemon();
                poke.setIdPokemon(rs.getInt("idPokemon"));
                poke.setNomePokemon(rs.getString("nomePokemon"));
                poke.setEspecie(rs.getString("especie"));
                poke.setPeso(rs.getDouble("peso"));
                poke.setAltura(rs.getDouble("altura"));
                poke.setIdTreinador(rs.getInt("idTreinador"));
                poke.setFoto(rs.getString("foto"));
            }
            return poke;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }
}
