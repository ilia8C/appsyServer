/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import crypt.SendEmail;
import entities.LastSignIn;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
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
 * This class is automatically generated thanks to the User entity, it contains
 * the basic management methods of the entity as well as customized ones.
 *
 * @author Alain Lozano
 */
@Stateless
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    //This method is used to create new users.
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(User entity) {
        super.create(entity);
    }

    //This method is used to edit existing users by the id.
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, User entity) {
        super.edit(entity);
    }

    //This method is used to delete users by the id.
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    //This method is used to search for a users by the id.
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    //This method is used to get all the users.
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findAll() {
        return super.findAll();
    }

    //This method is used to get a certaint amount of users.
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    //This method is used to count how many users are in the database.
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
    //This method is used so that a user can log in with his login and password
    //and in case it is not found, an error is sent to him.

    @GET
    @Path("login/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLoginAndPassword(@PathParam("login") String login, @PathParam("password") String password) throws Exception {
        User user = null;
        try {
            user = (User) em.createNamedQuery("findUserByLoginAndPassword")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            List<LastSignIn> lastSignIns = new ArrayList<>();
            lastSignIns = (ArrayList) em.createNamedQuery("lastSignInsByLogin").setParameter("login", login).getResultList();
            if (lastSignIns.size() < 10) {
                LastSignIn lastSignIn = new LastSignIn();
                lastSignIn.setId(null);
                lastSignIn.setLastSignIn(new Date());
                lastSignIn.setUser(user);
                em.persist(lastSignIn);
            } else {
                LastSignIn lis = lastSignIns.get(0);
                lis.setLastSignIn(new Date());
                em.merge(lis);
            }
        } catch (NoResultException e) {
            throw new NotAuthorizedException(e);
        } catch (Exception e) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return user;
    }

    //This method is used to search for a user through their login,
    //and if not found an error is sent to the user.
    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLogin(@PathParam("login") String login) throws Exception {
        User user = null;
        try {
            user = (User) em.createNamedQuery("findUserByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException(e);
        } catch (Exception e) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return user;
    }

    //This method is used to reset the password of a user by receiving his email,
    //a new password is generated by calling the generatePassword method and 
    //the SendEmail class is called to send an email to the user with the password change,  
    //if the user is not found an error message is sent.
    @GET
    @Path("resetPassword/{email}")
    @Produces({MediaType.APPLICATION_XML})
    public void resetPasswordByEmail(@PathParam("email") String email) throws Exception {
        User user = null;
        String password = generatePassword(8);
        try {
            user = (User) em.createNamedQuery("resetPasswordByEmail")
                    .setParameter("email", email)
                    .getSingleResult();
            user.setPassword(password);
            em.merge(user);
            SendEmail.sendEmail(email, user.getPassword(), user.getLogin());
        } catch (NoResultException e) {
            throw new NotFoundException(e);
        } catch (Exception e) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    //This method is used to change the password of a user through his login 
    //and receiving the new password, if the user is not found an error message is sent.
    @GET
    @Path("changePassword/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    public void changePasswordByLogin(@PathParam("login") String login, @PathParam("password") String password) throws Exception {
        User user = null;
        try {
            user = (User) em.createNamedQuery("resetPasswordByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
            user.setPassword(password);
            em.merge(user);
        } catch (NoResultException e) {
            throw new NotFoundException(e);
        } catch (Exception e) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    /**
     * This method is used to generate random passwords by receiving the length of the password to be generated.
     * @param length The length of the password to be generated
     * @return the generated password
     */
    private String generatePassword(int length) {
        String NUMBERS = "0123456789";

        String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

        String pswd = "";
        String key = NUMBERS + UPPERCASE + LOWERCASE;

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }

}
