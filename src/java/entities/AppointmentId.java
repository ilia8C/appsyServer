/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
}
