/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for the psychologist, it extends from the entity User which gives the
 * attributes: idUser, password, privilege, status, fullName, email and login.
 * This entity has the fields: specialization and office
 *
 * @author Ilia Consuegra
 */
@NamedQueries({
    //This is the query to for a user to log in with login and password.
    @NamedQuery(
            name = "findPsychologistByFullName", query = "SELECT p FROM Psychologist p WHERE p.fullName=:fullName"
    ),
    //This is the query to find a user by their login.
    @NamedQuery(
            name = "findPsychologistByMail", query = "SELECT p FROM Psychologist p  WHERE p.email=:email"
    ),
})
@Entity
@Table(name = "psychologist", schema = "appsydb")
@XmlRootElement
public class Psychologist extends User implements Serializable {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    private static final long serialVersionUID = 1L;
    private String specialization;
    /**
     * The attribute for the office of the psychologist
     */

    private String office;
    @OneToMany(mappedBy="psychologist", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Resource> resources;
    @OneToMany(mappedBy="psychologist", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Appointment> appointments;

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


