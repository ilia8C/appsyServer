/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entities.Appointment;
import entities.AppointmentId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author 2dam
 */
@Stateless
@Path("entities.appointment")
public class AppointmentFacadeREST extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "AppsyServerPU")
    private EntityManager em;

    private AppointmentId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;psychologistId=psychologistIdValue;clientId=clientIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.AppointmentId key = new entities.AppointmentId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> psychologistId = map.get("psychologistId");
        if (psychologistId != null && !psychologistId.isEmpty()) {
            key.setPsychologistId(new java.lang.Integer(psychologistId.get(0)));
        }
        java.util.List<String> clientId = map.get("clientId");
        if (clientId != null && !clientId.isEmpty()) {
            key.setClientId(new java.lang.Integer(clientId.get(0)));
        }
        return key;
    }

    public AppointmentFacadeREST() {
        super(Appointment.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Appointment entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") PathSegment id, Appointment entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entities.AppointmentId key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Appointment find(@PathParam("id") PathSegment id) {
        entities.AppointmentId key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<Appointment> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Appointment> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("psychologistId/{psychologistId}")
    @Produces({MediaType.APPLICATION_XML})
    public Set<Appointment> findAppointmentsByPsychologist (@PathParam("psychologistId") Integer psychologistId){
        Set<Appointment> appointments = null;
        try{
            appointments = new HashSet<>(em.createNamedQuery("findAppointmentsByPsychologist")
                .setParameter("psychologistId", psychologistId)
                .getResultList());
        
        }catch(Exception e){
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return appointments;
        
    }
    
    @GET
    @Path("clientId/{clientId}")
    @Produces({MediaType.APPLICATION_XML})
    public Set<Appointment> findAppointmentsByClient (@PathParam("clientId") Integer clientId){
        Set<Appointment> appointments = null;
        try{
            appointments = new HashSet<>(em.createNamedQuery("findAppointmentsByClient")
                .setParameter("clientId", clientId)
                .getResultList());
        
        }catch(Exception e){
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return appointments;
        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
