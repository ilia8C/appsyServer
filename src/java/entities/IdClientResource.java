/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Matteo Fernández
 */
@Embeddable
public class IdClientResource implements Serializable {

    private Integer idClient;
    private Integer idResource;

    public IdClientResource() {
    }

    public IdClientResource(Integer idClient, Integer idResource) {
        this.idClient = idClient;
        this.idResource = idResource;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idClient);
        hash = 97 * hash + Objects.hashCode(this.idResource);
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
        final IdClientResource other = (IdClientResource) obj;
        if (!Objects.equals(this.idClient, other.idClient)) {
            return false;
        }
        if (!Objects.equals(this.idResource, other.idResource)) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the idClient
     */
    public Integer getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the idResource
     */
    public Integer getIdResource() {
        return idResource;
    }

    /**
     * @param idResource the idResource to set
     */
    public void setIdResource(Integer idResource) {
        this.idResource = idResource;
    }

   

}
