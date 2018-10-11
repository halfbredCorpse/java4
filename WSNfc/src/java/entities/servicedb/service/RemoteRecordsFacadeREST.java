/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.servicedb.service;

import entities.servicedb.RemoteRecords;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jasmin Rose
 */
@Stateless
@Path("entities.servicedb.remoterecords")
public class RemoteRecordsFacadeREST extends AbstractFacade<RemoteRecords> {

    @PersistenceContext(unitName = "WSNfcPU")
    private EntityManager em;

    public RemoteRecordsFacadeREST() {
        super(RemoteRecords.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(RemoteRecords entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, RemoteRecords entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public RemoteRecords find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RemoteRecords> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RemoteRecords> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Path("json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createJSON(RemoteRecords entity) {
        super.create(entity);
    }
    
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RemoteRecords> findJSON() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
