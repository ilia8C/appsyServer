/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity for the appointments, it has the attributes: psychologist, client, date, diagnose, numApppointment and price.
 * Ilia Consuegra
 */
@Entity
public class Appointment implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Psychologist psychologist;
    private Client client;
    private LocalDateTime date;
    private String diagnose;
    private Integer numAppointment;
    private Float price;

    
/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Appointment[ id=" + id + " ]";
    }
    */

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return the diagnose
     */
    public String getDiagnose() {
        return diagnose;
    }

    /**
     * @param diagnose the diagnose to set
     */
    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    /**
     * @return the numAppointment
     */
    public Integer getNumAppointment() {
        return numAppointment;
    }

    /**
     * @param numAppointment the numAppointment to set
     */
    public void setNumAppointment(Integer numAppointment) {
        this.numAppointment = numAppointment;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the psychologist
     */
    public Psychologist getPsychologist() {
        return psychologist;
    }

    /**
     * @param psychologist the psychologist to set
     */
    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
