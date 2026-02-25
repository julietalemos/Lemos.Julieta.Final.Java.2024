/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import exceptions.PersistenciaException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import interfaces.Persistencia;
import models.*;
import models.enums.*;

import java.io.BufferedReader;
import java.io.FileReader;


public class CSVService implements Persistencia<Vehiculo> {
    //private final String path;
    
    
    /*public String getResultado() {
        return resultado;
    }*/
    
    private String construirLinea(Vehiculo v){
        StringBuilder sb = new StringBuilder();
        sb.append(v.getId()).append(",");
        sb.append(v.getTipo()).append(",");
        sb.append(v.getPatente()).append(",");
        sb.append(v.getMarca()).append(",");
        sb.append(v.getModelo()).append(",");
        sb.append(v.getAnio()).append(",");
        sb.append(v.getPrecio()).append(",");
        sb.append(v.getCombustible()).append(",");
        sb.append(v.getEstado()).append(",");
        
        if(v instanceof Auto auto){
            sb.append(auto.getNumPuertas()).append(",");
            sb.append(auto.getTieneAireAcondicionado()).append(",");
        } else if(v instanceof Moto moto){
            sb.append(moto.getCilindrada()).append(",");
            sb.append(moto.getEsDeportiva()).append(",");          
        } else if(v instanceof Camion camion){
            sb.append(camion.getCapacidadCarga()).append(",");
            sb.append(camion.getNumRuedas()).append(",");
        }
        return sb.toString();                   
    }

    /* 
     * @brief Constructor de la clase CSVService
     * @param path: ruta del archivo CSV donde se guardarán los vehículos
     *//*
    public CSVService(String path) {
        this.path = path;
    }*/
    
    /**
     * Parsea una línea CSV y crea el vehículo correcto
     */
    private Vehiculo parsearLineaCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 9) return null;
        
        try {
            // Parsear atributos comunes
            int id = Integer.parseInt(partes[0]);
            TipoVehiculo tipo = TipoVehiculo.valueOf(partes[1]);
            String patente = partes[2];
            String marca = partes[3];
            String modelo = partes[4];
            int anio = Integer.parseInt(partes[5]);
            double precio = Double.parseDouble(partes[6]);
            TipoCombustible combustible = TipoCombustible.valueOf(partes[7]);
            EstadoVehiculo estado = EstadoVehiculo.valueOf(partes[8]);
            
            // Crear el vehículo según el tipo
            return switch (tipo) {
                case AUTO -> {
                    int puertas = Integer.parseInt(partes[9]);
                    boolean ac = Boolean.parseBoolean(partes[10]);
                    yield new Auto(id, patente, marca, modelo, anio, precio,
                        combustible, estado, puertas, ac);
                }
                case MOTO -> {
                    int cilindrada = Integer.parseInt(partes[9]);
                    boolean deportiva = Boolean.parseBoolean(partes[10]);
                    yield new Moto(id, patente, marca, modelo, anio, precio,
                        combustible, estado, cilindrada, deportiva);
                }
                case CAMION -> {
                    int carga = Integer.parseInt(partes[9]);
                    int ruedas = Integer.parseInt(partes[10]);
                    yield new Camion(id, patente, marca, modelo, anio, precio,
                        combustible, estado, carga, ruedas);
                }
            };
        } catch (Exception e) {
            System.err.println("Error al parsear línea: " + linea);
            return null;
        }
    }

    @Override
    public void guardar(List<Vehiculo> lista, String path) throws PersistenciaException{
        StringBuilder sb = new StringBuilder();
        for(Vehiculo vehiculo : lista){
            sb.append(construirLinea(vehiculo));
            sb.append("\n");
        }

        try (FileWriter fw = new FileWriter(path)) {
            fw.write(sb.toString());
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar CSV: " + e.getMessage(), e);
        }
    }
    
    /*@Override
    public void guardar(List<? extends Vehiculo> vehiculos, String ruta) 
            throws PersistenciaException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(ruta))) {
            // Encabezado
            writer.write("ID,Tipo,Patente,Marca,Modelo,Anio,Precio,Combustible,Estado,Extra1,Extra2\n");
            
            // Guardar cada vehículo
            for (Vehiculo v : vehiculos) {
                writer.write(construirLineaCSV(v) + "\n");
            }
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar CSV: " + e.getMessage(), e);
        }
    }*/

    @Override
    public List<Vehiculo> cargar(String path) throws PersistenciaException{
        List<Vehiculo> vehiculos = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while((line = reader.readLine()) != null){
                Vehiculo v = parsearLineaCSV(line);
                if (v != null) {
                    vehiculos.add(v);
                }

//String[] vehiculo = line.split(",");

                /*int id = Integer.parseInt(vehiculo[0]);
                String patente = vehiculo[1];
                String marca = vehiculo[2];
                String modelo = vehiculo[3];
                int anio = Integer.parseInt(vehiculo[4]);
                String tipo = vehiculo[5];
                TipoCombustible combustible = TipoCombustible.valueOf(vehiculo[6]);
                EstadoVehiculo estado = EstadoVehiculo.valueOf(vehiculo[7]);
                double precio = Double.parseDouble(vehiculo[8]);

                vehiculos.add(
                    (tipo == "AUTO") ? new Auto  (id, patente, marca, modelo, anio, precio, combustible, estado, Integer.parseInt(vehiculo[9]), Boolean.parseBoolean(vehiculo[10])) :
                    (tipo == "MOTO") ? new Moto  (id, patente, marca, modelo, anio, precio, combustible, estado, Integer.parseInt(vehiculo[9]), Boolean.parseBoolean(vehiculo[10])) :
                                       new Camion(id, patente, marca, modelo, anio, precio, combustible, estado, Integer.parseInt(vehiculo[9]), Integer.parseInt(vehiculo[10]))
                );*/
            }
            return vehiculos;
        } catch(IOException e){
            throw new PersistenciaException("Error al cargar CSV: " + e.getMessage(), e);
        }
        //return vehiculos; esto va arriba en el try
    }
}