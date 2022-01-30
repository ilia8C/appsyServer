/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javafx.scene.control.Label;

/**
 *
 * @author  Alain Lozano,Ilia Consuegra
 */
public class PasswordDontMatch extends Exception {

    /**
     * Constructs an instance of <code>EmptyFieldsException</code> with the
     * specified detail message.
     *
     */
    public PasswordDontMatch() {
        super("The password dont match.");
    }
}