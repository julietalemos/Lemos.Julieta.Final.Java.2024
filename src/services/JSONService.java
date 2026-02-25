/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exceptions.PersistenciaException;
import interfaces.Persistencia;
import models.Vehiculo;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class JSONService implements Persistencia<Vehiculo> {

    @Override
    public void guardar(List<Vehiculo> lista, String path) throws PersistenciaException{
        try (PrintWriter writer = new PrintWriter(new File(path))) {
            Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Vehiculo.class, new VehiculoAdapter())
                .create();
            gson.toJson(lista, writer);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar Json: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Vehiculo> cargar(String path) throws PersistenciaException{
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Vehiculo.class, new VehiculoAdapter())
                        .create();
            Type listType = new TypeToken<List<Vehiculo>>(){}.getType(); 
            List<Vehiculo> lista = gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (IOException e) {
            throw new PersistenciaException("Error al cargar Json: " + e.getMessage(), e);
        }
    }
}
