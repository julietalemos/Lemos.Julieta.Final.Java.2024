/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author julie
 */
public class PersistenciaException extends RuntimeException{
    
    public PersistenciaException(String mensaje){
        super(mensaje);
    }
    
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
