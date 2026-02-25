/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author julie
 */
public class PrecioInvalidoException extends Exception{
    public PrecioInvalidoException(String message) {
        super(message);
    }

    public PrecioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
