/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.entity.exceptions;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk
 */
public class BookingNotPossibleException extends Exception {

    /**
     * Creates a new instance of <code>BookingNotPossibleException</code>
     * without detail message.
     */
    public BookingNotPossibleException() {
    }

    /**
     * Constructs an instance of <code>BookingNotPossibleException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public BookingNotPossibleException(String msg) {
        super(msg);
    }
}
