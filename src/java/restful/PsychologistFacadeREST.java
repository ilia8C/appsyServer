/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import crypt.EncriptDecript;
import entities.Psychologist;
import entities.User;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ilia Consuegra
 */
@Stateless
@Path("entities.psychologist")
public class PsychologistFacadeREST extends AbstractFacade<Psychologist> {

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;

    public PsychologistFacadeREST() {
        super(Psychologist.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Psychologist entity) throws ClientErrorException{
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
    public void edit(@PathParam("id") Integer id, Psychologist entity) throws  ClientErrorException{
        try {
            super.edit(entity);
        } catch (Exception ex) {
            throw new ClientErrorException(409);
        }

    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) throws NotFoundException{
        try {
            super.remove(super.find(id));
        } catch (ClientErrorException ex) {
            throw new NotFoundException();
        }

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Psychologist find(@PathParam("id") Integer id) throws NotFoundException{
        Psychologist psychologist = null;
        try {
            psychologist = super.find(id);
        } catch (NoResultException ex) {
            throw new NotFoundException(ex);
        }
        return psychologist;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<Psychologist> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Psychologist> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML})
    public User findPsychologistByMail(@PathParam("email") String email) throws Exception {
        Psychologist psychologist = null;
        try {
            psychologist = (Psychologist) em.createNamedQuery("findPsychologistByMail")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        }
        return psychologist;
    }

    @GET
    @Path("fullName/{fullName}")
    @Produces({MediaType.APPLICATION_XML})
    public User findPsychologistByFullName(@PathParam("fullName") String fullName) throws Exception {
        Psychologist psychologist = null;
        try {
            psychologist = (Psychologist) em.createNamedQuery("findPsychologistByFullName")
                    .setParameter("fullName", fullName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new NotFoundException();
        }
        return psychologist;
    }

}
