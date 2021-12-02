/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;

/**
 * This class is for the Client entity, it has the attributes: dateStart and the
 * User attributes: idUser, password, enumPrivilege, enumStatus, fullName, email
 * and login.
 *
 * @author Alain Lozano Isasi
 */
@Entity
public class Client extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate dateStart;

    /**
     * @return the dateStart
     */
    public LocalDate getDateStart() {
        return dateStart;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

}
