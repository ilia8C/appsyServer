/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for the psychologist, it extends from the entity User which gives the
 * attributes: idUser, password, privilege, status, fullName, email and login.
 * This entity has the fields: specialization and office
 *
 * @author Ilia Consuegra
 */
@Entity
@Table(name="psychologist", schema="appsydb")
@XmlRootElement
public class Psychologist extends User implements Serializable{

    
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private static final long serialVersionUID = 1L;
    private String specialization;
    /**
     * The attribute for the office of the psychologist
     */
    
    private String office;
    @OneToMany(mappedBy="psychologist")
    private Set<Resource> resources;
    @OneToMany(mappedBy="psychologist")
    private Set<Appointment> appointments;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.specialization);
        hash = 97 * hash + Objects.hashCode(this.office);
        hash = 97 * hash + Objects.hashCode(this.resources);
        hash = 97 * hash + Objects.hashCode(this.appointments);
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
        final Psychologist other = (Psychologist) obj;
        if (!Objects.equals(this.specialization, other.specialization)) {
            return false;
        }
        if (!Objects.equals(this.office, other.office)) {
            return false;
        }
        if (!Objects.equals(this.resources, other.resources)) {
            return false;
        }
        if (!Objects.equals(this.appointments, other.appointments)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Psychologist{" + "specialization=" + specialization + ", office=" + office + ", resources=" + resources + ", appointments=" + appointments + '}';
    }

   

    /**
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * @param specialization the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * @return the office
     */
    public String getOffice() {
        return office;
    }

    /**
     * @param office the office to set
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * @return the resources
     */
    
    @XmlTransient
    public Set<Resource> getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    /**
     * @return the appointments
     */
    
    @XmlTransient
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * @param appointments the appointments to set
     */
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }


}
