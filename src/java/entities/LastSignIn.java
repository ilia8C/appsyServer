/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "lastSignIn", schema = "appsydb")
@XmlRootElement
public class LastSignIn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSignIn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idUser
     */
    @XmlTransient
    public User getUser() {
        return user;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the lastSignIn
     */
    public Date getLastSignIn() {
        return lastSignIn;
    }

    /**
     * @param lastSignIn the lastSignIn to set
     */
    public void setLastSignIn(Date lastSignIn) {
        this.lastSignIn = lastSignIn;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final LastSignIn other = (LastSignIn) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.lastSignIn, other.lastSignIn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LastSignIn{" + "id=" + id + ", idUser=" + user + ", lastSignIn=" + lastSignIn + '}';
    }

}
