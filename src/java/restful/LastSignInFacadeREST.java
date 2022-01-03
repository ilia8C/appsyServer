/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entities.LastSignIn;
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
 * This class is automatically generated thanks to the LstSignIn entity, it contains
 * the basic management methods of the entity.
 * @author Alain Lozano
 */
@Stateless
@Path("entities.lastsignin")
public class LastSignInFacadeREST extends AbstractFacade<LastSignIn> {

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;

    public LastSignInFacadeREST() {
        super(LastSignIn.class);
    }
    //This method is used to create a new signIn.
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(LastSignIn entity) {
        super.create(entity);
    }
    //This method is used to edit an existing signIn by the id.
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, LastSignIn entity) {
        super.edit(entity);
    }
    //This method is used to delete a signIn by the id.
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    //This method is used to search for a signIn by the id.
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public LastSignIn find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    //This method is used to get all the signIns.
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<LastSignIn> findAll() {
        return super.findAll();
    }
    //This method is used to get a certaint amount of signIns.
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<LastSignIn> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    //This method is used to count how many singIns are in the database.
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
