/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Matteo Fernández
 */
@Entity
public class ClientResource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EmbeddedId 
    private IdClientResource idClientResource;
    private String typeDiagnose;

    public IdClientResource getIdClientResource() {
        return idClientResource;
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
}
