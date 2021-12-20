/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
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
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("login/{login}/password/{password}")
    @Produces({MediaType.APPLICATION_XML})   
    public User findUserByLoginAndPassword(@PathParam("login") String login,@PathParam("password") String password) throws Exception {
        User user = null;
        try{
            user= (User) em.createNamedQuery("findUserByLoginAndPassword")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        }catch(NoResultException e){
            throw new NotAuthorizedException(e);
        }catch(Exception e){
           Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return user;
    }
    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_XML})   
    public User findUserByLogin(@PathParam("login") String login) throws Exception {
        User user = null;
        try{
            user= (User) em.createNamedQuery("findUserByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
        }catch(NoResultException e){
            throw new NotFoundException(e);
        }catch(Exception e){
           Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return user;
    }
    @GET
    @Path("resetPassword/{login}")
    @Produces({MediaType.APPLICATION_XML})   
    public void resetPasswordByLogin(@PathParam("login") String login) throws Exception {
        User user = null;
        String password="abcd*1234";
        try{
            user= (User) em.createNamedQuery("resetPasswordByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
            user.setPassword(password);
            em.merge(user);
        }catch(NoResultException e){
            throw new NotFoundException(e);
        }catch(Exception e){
           Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }
    @GET
    @Path("changePassword/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})   
    public void changePasswordByLogin(@PathParam("login") String login,@PathParam("password") String password) throws Exception {
        User user = null;
        try{
            user= (User) em.createNamedQuery("resetPasswordByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
            user.setPassword(password);
            em.merge(user);
        }catch(NoResultException e){
            throw new NotFoundException(e);
        }catch(Exception e){
           Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }
    
}
