/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for the appointments, it has the attributes: psychologist, client,
 * date, diagnose, numApppointment and price. Ilia Consuegra
 */
@NamedQueries({
    @NamedQuery(
            name = "findAppointmentsOfPsychologist",
            query = "SELECT a FROM Appointment a WHERE a.appointmentId.psychologistId=:psychologistId"
    ),
    @NamedQuery(
            name = "findAppointmentsOfClient",
            query = "SELECT a FROM Appointment a WHERE a.appointmentId.clientId=:clientId"
    ),
    @NamedQuery(
            name = "findAppointmentsOfClientByPsychologist",
            query = "SELECT a FROM Appointment a WHERE a.appointmentId.clientId=:clientId AND a.appointmentId.psychologistId=:psychologistId"                  
    ),
    @NamedQuery(
            name = "findAppointmentsByDate",
            query = "SELECT a FROM Appointment a WHERE a.date=:date"
    ),
    @NamedQuery(
            name = "deleteAppointment",
            query = "DELETE FROM Appointment a WHERE a.appointmentId.clientId=:clientId and a.appointmentId.psychologistId=:psychologistId "
    )
})
@Entity
@Table(name = "appointment", schema = "appsydb")
@XmlRootElement
public class Appointment implements Serializable {

    private static long serialVersionUID = 1L;

    @EmbeddedId
    private AppointmentId appointmentId;
    //@MapsId("psychologistId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "psychologistId", updatable = false, insertable = false)
    private Psychologist psychologist;
    //@MapsId("clientId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId", updatable = false, insertable = false)
    private Client client;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String diagnose;
    private Integer numAppointment;
    private Float price;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.appointmentId);
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
        final Appointment other = (Appointment) obj;
        if (!Objects.equals(this.diagnose, other.diagnose)) {
            return false;
        }
        if (!Objects.equals(this.appointmentId, other.appointmentId)) {
            return false;
        }
        if (!Objects.equals(this.psychologist, other.psychologist)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", psychologist=" + psychologist + ", client=" + client + ", date=" + date + ", diagnose=" + diagnose + ", numAppointment=" + numAppointment + ", price=" + price + '}';
    }

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
     * @return the appointmentId
     */
    public AppointmentId getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(AppointmentId appointmentId) {
        this.appointmentId = appointmentId;
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
    @XmlTransient
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
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

}
