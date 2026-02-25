/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import exceptions.PersistenciaException;
import interfaces.Persistencia;
import models.Vehiculo;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;


public class DATService implements Persistencia<Vehiculo> {
    //private final String path;

    /* 
     * @brief Constructor de la clase DATService
     * @param path: ruta del archivo DAT donde se guardarán los vehículos
     *//*
    public DATService(String path) {
        this.path = path;
    }*/

    @Override
    public void guardar(List<Vehiculo> vehiculos, String path) throws PersistenciaException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(vehiculos);
        } catch(IOException e) {
            throw new PersistenciaException("Error al serializar: " + e.getMessage());
        }
    }

    @Override
    public List<Vehiculo> cargar(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (List<Vehiculo>) ois.readObject();
        } catch(IOException | ClassNotFoundException e) {
            throw new PersistenciaException("Error al deserializar: " + e.getMessage());
        }
    }
}