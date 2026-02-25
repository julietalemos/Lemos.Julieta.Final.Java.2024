/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import exceptions.PersistenciaException;

/**
 *
 * @author julie
 * @brief Interfaz para operaciones de persistencia de objetos.
 * @param <T> El tipo de objeto que se va a persistir.
 */
public interface Persistencia<T>  {
    void guardar(List<T> lista, String path) throws PersistenciaException;
    List<T> cargar(String path) throws PersistenciaException;
}
