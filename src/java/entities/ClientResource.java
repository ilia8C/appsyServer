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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matteo Fernández
 */
@Entity
@Table(name = "clientresouce", schema = "appsydb")
@XmlRootElement
public class ClientResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdClientResource idClientResource;
    //@MapsId("idResource")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idResource", updatable = false, insertable = false)
    private Resource resource;
    //@MapsId("idClient")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idClient", updatable = false, insertable = false)
    private Client client;
    private String typeDiagnose;

    @XmlTransient
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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