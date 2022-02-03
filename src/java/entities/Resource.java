/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the code entity for the Resource class.
 *
 * @author Matteo Fernández
 */
@NamedQueries({
    @NamedQuery(
            name = "getAllResourcesByTittle", query = "SELECT r FROM Resource r WHERE r.tittle=:tittle"
    ),
    @NamedQuery(
            name = "getAllResourcesByPsychologist", query = "SELECT r FROM Resource r WHERE r.psychologist.id=:id"
    ),
    @NamedQuery(
            name = "getPsychologistByResourcesId", query = "SELECT r FROM Resource r, Psychologist p WHERE r.psychologist.id=p.id and r.id=:id"
    )
})

@Entity
@Table(name = "resource", schema = "appsydb")
@XmlRootElement
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Psychologist psychologist;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    private String tittle;
    @OneToMany(mappedBy = "resource", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ClientResource> clientResource;

    /**
     * Is used to ask for a list of ClientResources.
     *
     * @return a list of ClientResources.
     */
    @XmlTransient
    public Set<ClientResource> getClientResource() {
        return clientResource;
    }

    /**
     * Is used to ask for a list of ClientResources.
     *
     * @param clientResource the object clientResource.
     */
    public void setClientResource(Set<ClientResource> clientResource) {
        this.clientResource = clientResource;
    }

    /**
     * Is used to ask for the Psychologist.
     *
     * @return the object Psychologist.
     */
    
    public Psychologist getPsychologist() {
        return psychologist;
    }

    /**
     * Is used to ask for the Psychologist.
     *
     * @param psychologist the object Psychologist.
     */
    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    /**
     * Is used to ask for the id of the Resource.
     *
     * @return the Resource id, that is the Primary key.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Puts the id of the Resource.
     *
     * @param id is the id that is the primary key.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Is used to ask for the link of the Resource.
     *
     * @return the link of the Resource.
     */
    public String getLink() {
        return link;
    }

    /**
     * Puts the Link of the Resource.
     *
     * @param link is a String with a link inside.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Is used to ask for the Date Added of the Resource.
     *
     * @return the date added.
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * Puts the dateAdded of the Resource.
     *
     * @param dateAdded the date of the Resource
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * Is used to ask for the tittle of the Resource.
     *
     * @return the tittle of the Resource.
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * Puts the tittle of the Resource.
     *
     * @param tittle value for the Resource tittle.
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Resource[ id=" + id + " ]";
    }

}
