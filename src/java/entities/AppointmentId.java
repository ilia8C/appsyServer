/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 *
 * @author 2dam
 */
@Embeddable
public class AppointmentId implements Serializable{

    private Integer psychologistId;
    private Integer clientId;
    
    public AppointmentId() {
    }

    public AppointmentId(Integer psychologistId, Integer clientId) {
        this.psychologistId = psychologistId;
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.psychologistId);
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
        final AppointmentId other = (AppointmentId) obj;
        if (!Objects.equals(this.psychologistId, other.psychologistId)) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the psychologistId
     */
    public Integer getPsychologistId() {
        return psychologistId;
    }

    /**
     * @param psychologistId the psychologistId to set
     */
    public void setPsychologistId(Integer psychologistId) {
        this.psychologistId = psychologistId;
    }

    /**
     * @return the clientId
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
   
}
