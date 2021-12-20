/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is for the Client entity, it has the attributes: dateStart and the
 * User attributes: idUser, password, enumPrivilege, enumStatus, fullName, email
 * and login.
 *
 * @author Alain Lozano Isasi
 */
@Entity
@Table(name = "client", schema = "appsydb") 
@XmlRootElement
public class Client extends User implements Serializable{

    private static final long serialVersionUID = 1L;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    
    @OneToMany(mappedBy = "client")
    private Set<Appointment> appointments;
    
     @OneToMany(mappedBy = "client")
    private Set<ClientResource> clientResources;
     
       @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.dateStart);
        hash = 67 * hash + Objects.hashCode(this.appointments);
        hash = 67 * hash + Objects.hashCode(this.clientResources);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.dateStart, other.dateStart)) {
            return false;
        }
        if (!Objects.equals(this.appointments, other.appointments)) {
            return false;
        }
        if (!Objects.equals(this.clientResources, other.clientResources)) {
            return false;
        }
        return true;
    }

    /**
     * @return the dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    
     
    /**
     * @return the appointments
     */
    
    
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * @param appointments the appointments to set
     */
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * @return the clientResources
     */
    
    
    public Set<ClientResource> getClientResources() {
        return clientResources;
    }

    /**
     * @param clientResources the clientResources to set
     */
    public void setClientResources(Set<ClientResource> clientResources) {
        this.clientResources = clientResources;
    }
    

}
