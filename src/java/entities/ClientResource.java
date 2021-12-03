/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matteo Fernández
 */
@Entity
@Table(name = "clientresouce", schema = "appsydb")
@XmlRootElement
public class ClientResource implements Serializable {

    @MapsId("idResource")
    @ManyToOne
    private Resource resource;
    @MapsId("idClient")
    @ManyToOne
    private Client client;
    private static final long serialVersionUID = 1L;
    
    
    @EmbeddedId
    private IdClientResource idClientResource;
    private String typeDiagnose;

    public IdClientResource getIdClientResource() {
        return idClientResource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setIdClientResource(IdClientResource idClientResource) {
        this.idClientResource = idClientResource;
    }

    public String getTypeDiagnose() {
        return typeDiagnose;
    }

    public void setTypeDiagnose(String typeDiagnose) {
        this.typeDiagnose = typeDiagnose;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.resource);
        hash = 17 * hash + Objects.hashCode(this.idClientResource);
        hash = 17 * hash + Objects.hashCode(this.typeDiagnose);
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
        final ClientResource other = (ClientResource) obj;
        if (!Objects.equals(this.typeDiagnose, other.typeDiagnose)) {
            return false;
        }
        if (!Objects.equals(this.resource, other.resource)) {
            return false;
        }
        if (!Objects.equals(this.idClientResource, other.idClientResource)) {
            return false;
        }
        return true;
    }

}
