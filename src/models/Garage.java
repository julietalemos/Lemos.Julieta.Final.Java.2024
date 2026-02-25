/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import comparators.IdComparator;
import exceptions.PersistenciaException;
import exceptions.VehiculoException;
import interfaces.*;
import iterator.VehiculoIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Iterator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Predicate;
import models.enums.EstadoVehiculo;


public class Garage implements Crud<Vehiculo>, Iterable<Vehiculo>, Exportable{
    private List<Vehiculo> vehiculos;

    /* 
     * @brief Constructor de la clase Garage
     */
    public Garage () {
        this.vehiculos = new ArrayList<>();
    }

    /* 
     * @brief Método para obtener la lista inmodificable de vehículos del garage
     * @return lista de vehículos protegida del garage
     * encapsulamiento
     */
    public List<Vehiculo> getVehiculos() {
        return Collections.unmodifiableList(vehiculos);
        //return vehiculos;
    }
    
    /* 
     * @brief Método para obtener el siguiente ID disponible para un nuevo vehículo
     * @param vehiculos: lista de vehículos para verificar los IDs existentes
     * @return siguiente ID disponible para un nuevo vehículo
    */
    public int getNextId() {
        reiniciarFiltros();
        for(int i = 0; i < vehiculos.size(); i++){
            if (vehiculos.get(i).getId() != i){
                return i;
            }
        }
        return vehiculos.size();
    }
    
    // -------- CRUD ----------

    @Override
    public void crear(Vehiculo vehiculo) throws VehiculoException {
        if(vehiculo == null){
            throw new VehiculoException("El vehiculo no puede ser nulo");
        }
        int idCorrecto = getNextId();
        vehiculo.setId(idCorrecto);
        this.vehiculos.add(vehiculo);
    }

    @Override
    public Vehiculo leer(int id) throws VehiculoException {
        for (Vehiculo v : this.vehiculos) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
        //throw new VehiculoException("No existe vehículo con el ID seleccionado");
    }

    @Override
    public void actualizar(int id, Vehiculo vehiculo) throws VehiculoException {
        vehiculo.setId(id);    
        // Buscar y reemplazar
        boolean encontrado = false;
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getId() == id) {   
                vehiculos.set(i, vehiculo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new VehiculoException("Vehículo con ID " + id + " no encontrado");
        }
    }

    @Override
    public void eliminar(int id) throws VehiculoException{
        boolean eliminado = this.vehiculos.removeIf(v -> v.getId() == id);
        if (!eliminado) {
            throw new VehiculoException("No se pudo eliminar el vehículo con ID: " + id);
        }
    }
    
    // ----- FILTRADO -------

    /* 
     * @brief Método para filtrar vehículos por marca
     * @param marca: marca por la que se desea filtrar
     * @return lista de vehículos o subtipos que coinciden con la marca
     */
    public List<? extends Vehiculo> filtrarPorMarca(String marca){
        return vehiculos.stream()
            .filter(v -> v.getMarca().toLowerCase().contains(marca.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    /**
     * Filtra por estado
     * @param Enum como parámetro
     * @return lista de Vehiculos o subtipos
     */
    public List<? extends Vehiculo> filtrarPorEstado(EstadoVehiculo estado) {
        return vehiculos.stream()
            .filter(v -> v.getEstado() == estado)
            .collect(Collectors.toList());
    }
    
    // -------- INTERFACES FUNCIONALES --------
    /**
     * REQUISITO: Consumer
     * Aplica una operación a TODOS los vehículos
     */
    public void aplicarDescuento(Consumer<Vehiculo> operacion) {
        vehiculos.forEach(operacion);
    }
    
    /**
     * REQUISITO: Predicate + Consumer
     * Aplica operación solo a vehículos que cumplan condición
     */
    public void aplicarCondicional(Predicate<Vehiculo> condicion, Consumer<Vehiculo> operacion) {
        vehiculos.stream()
            .filter(condicion)
            .forEach(operacion);
    }

    
    // ----- ORDENAMIENTO -------
    /* 
     * @brief Método para ordenar los vehículos según un comparador
     * @param comparator: comparador para ordenar los vehículos
     */
    public void ordenar(Comparator<Vehiculo> comparator) {
        this.vehiculos.sort(comparator);
    }
    
    public void reiniciarFiltros(){
        this.vehiculos.sort(new IdComparator());
    }
    
    // ------- ITERABLE ----------

    @Override
    public Iterator<Vehiculo> iterator(){
        return new VehiculoIterator(this.vehiculos);
    }

    @Override
    public void exportarTXT(String path){
        StringBuilder sb = new StringBuilder();
        sb.append("          LISTADO DE VEHÍCULOS - GARAGE\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        for (Vehiculo v : vehiculos){
            sb.append(v.toString());
            sb.append("\n\n");
        }
        sb.append("══════════════════ FIN DEL LISTADO ══════════════════\n\n");

        try (FileWriter fw = new FileWriter(path)) {
            fw.write(sb.toString());
        } catch (IOException e) {
            throw new PersistenciaException("Error al exportar TXT: " + e.getMessage(), e);
        }
    }
}