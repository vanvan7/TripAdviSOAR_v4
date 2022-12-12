/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author chris
 */
public class NoReviewException extends Exception {

    private String message;

    public NoReviewException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    
}
