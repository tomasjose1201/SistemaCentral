/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.sistemacentral;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Tom
 */
@Path("pokeagenda")
public class PokeAgendaResource {

    @Context
    private UriInfo context;

    public PokeAgendaResource() {
    }

    //Serviços de autenticação (login e senha);
    @GET
    @Path("/autenticar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticarTreinador() throws SQLException {
        SistemaCentralDao dao = new SistemaCentralDao();
        Treinador treinador = new Treinador();
        treinador.setLogin("tomjose");
        treinador.setSenha("tom");
        Treinador result = dao.findUser(treinador);
        String json = new Gson().toJson(result);
        return Response.ok(json).header("Access-Control-Allow-Origin", "*").build();
    }

    //Serviço de cadastro de novo Pokemon;
    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPokemon(Message message) throws SQLException {
        Map<String, String> parameters = message.getParameters();
        SistemaCentralDao dao = new SistemaCentralDao();
        Pokemon poke = new Pokemon();
        poke.setNomePokemon(parameters.get("nomePokemon"));
        poke.setEspecie(parameters.get("especie"));
        poke.setPeso(Double.parseDouble(parameters.get("peso")));
        poke.setAltura(Double.parseDouble(parameters.get("altura")));
        poke.setIdTreinador(Integer.parseInt(parameters.get("idTreinador")));
        dao.insert(poke);
        return Response.status(Response.Status.OK).build();
    }

    //Serviço de consulta de todos os Pokemons;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPokemons() throws SQLException {
        SistemaCentralDao dao = new SistemaCentralDao();
        ArrayList<Pokemon> pokemons = dao.selectAll();
        String json = new Gson().toJson(pokemons);
        return json;
    }

    //Serviço de pesquisa de um determinado Pokemon
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPokemon(@PathParam("id") int id) throws SQLException {
        SistemaCentralDao dao = new SistemaCentralDao();
        Pokemon poke = dao.selectById(id);
        String json = new Gson().toJson(poke);
        return json;
    }
}
