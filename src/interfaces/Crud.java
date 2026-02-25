/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import exceptions.VehiculoException;

/* 
 * @author julie
 * @brief Interfaz genérica para operaciones CRUD (Crear, Leer, Actualizar, Eliminar).
 * @param <T> El tipo de objeto que se va a manejar en las operaciones CRUD.
*/
public interface Crud<T> { 

    void crear(T obj) throws VehiculoException;
    T leer(int id) throws VehiculoException;
    void actualizar(int id, T obj) throws VehiculoException;
    void eliminar(int id) throws VehiculoException;
}