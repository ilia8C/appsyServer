/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entities.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
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
 * Defines REST services for the Resource entity.
 *
 * @author Matteo Fernández
 */
@Stateless
@Path("entities.resource")
public class ResourceFacadeREST extends AbstractFacade<Resource> {

    private static final Logger LOGGER = Logger.getLogger(ResourceFacadeREST.class.getName());

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;

    public ResourceFacadeREST() {
        super(Resource.class);
    }

    /**
     * Creates a new Resource
     *
     * @param entity
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Resource entity) {
        super.create(entity);
    }

    /**
     * Edits the resource by id
     *
     * @param id of the resources
     * @param entity
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, Resource entity) {
        super.edit(entity);
    }

    /**
     * Deletes de resource by id
     *
     * @param id of the resources
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    /**
     * Gets the resources by id
     *
     * @param id of the resources
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Resource find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * Gets a list of all resources
     *
     * @return
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<Resource> findAll() {
        return super.findAll();
    }

    /**
     * Gets a list for all the existence resources
     *
     * @param from
     * @param to
     * @return
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Resource> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * Gets a list for all the resources with the tittle its given.
     *
     * @param tittle the one that you want to search
     *
     * @return resources
     */
    @GET
    @Path("getResourcesByTittle/{tittle}")
    @Produces({MediaType.APPLICATION_XML})
    public Set<Resource> getAllResourcesByTittle(@PathParam("tittle") String tittle) {
        Set<Resource> resources = null;
        resources = new HashSet<>(em.createNamedQuery("getAllResourcesByTittle").setParameter("tittle", tittle).getResultList());
        return resources;
    }

    /**
     * Gets a list for all the resources with the Psychologist that has added
     * it, looking by the id of the psychologist.
     *
     * @param id of the psychologist that wants to search
     *
     * @return resources
     */
    @GET
    @Path("getResourcesByPsychologistId/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Set<Resource> getAllResourcesByPsychologist(@PathParam("id") Integer id) {
        Set<Resource> resources = null;
        resources = new HashSet<>(em.createNamedQuery("getAllResourcesByPsychologist").setParameter("id", id).getResultList());
        return resources;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
