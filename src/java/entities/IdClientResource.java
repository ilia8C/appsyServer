/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Matteo Fernández
 */
@Embeddable
class IdClientResource implements Serializable {

    private Integer idClient;
    private Integer idResource;

}
