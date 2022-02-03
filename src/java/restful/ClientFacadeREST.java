/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import crypt.EncriptDecript;
import crypt.SendEmail;
import entities.Client;
import entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 2dam
 */
@Stateless
@Path("entities.client")
public class ClientFacadeREST extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;
    private final Logger LOGGER=Logger.getLogger(this.getClass().getName());

    public ClientFacadeREST() {
        super(Client.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Client entity) {
        try {
            String passwordHash = EncriptDecript.hashearTexto(entity.getPassword().getBytes());
            entity.setPassword(passwordHash);
            super.create(entity);
        } catch (Exception ex) {
            throw new ClientErrorException(409);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, Client entity) throws ClientErrorException{
        try{
            LOGGER.log(Level.INFO, "Entering editing:{0}", entity.toString());
            super.edit(entity);
        }catch(Exception e){
             LOGGER.log(Level.SEVERE, "Exception editing:{0}", e.getLocalizedMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            super.remove(super.find(id));
        } catch (Exception ex) {
            throw new NotFoundException();

        }

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Client find(@PathParam("id") Integer id) {
        try {
            return super.find(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }

    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<Client> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Client> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

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

    //This method is used to search for a client through their full name,
    //and if not found an error is sent to the user.
    @GET
    @Path("fullName/{fullName}")
    @Produces({MediaType.APPLICATION_XML})
    public User findClientByFullName(@PathParam("fullName") String fullName) throws Exception {
        User user = null;
        try {
            user = (User) em.createNamedQuery("findClientByFullName")
                    .setParameter("fullname", fullName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
        return user;
    }
}
