/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.sistemacentral;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
    @POST
    @Path("/autenticar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticarTreinador() {
        return Response.status(Response.Status.OK).build();
    }
    
    //Serviço de cadastro de novo Pokemon;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPokemon() {
        return Response.status(Response.Status.OK).build();
    }
    
    //Serviço de consulta de todos os Pokemons;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPokemons() {
        throw new UnsupportedOperationException();
    }
    
    //Serviço de pesquisa de um determinado Pokemon
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPokemon() {
        throw new UnsupportedOperationException();
    }
}
