/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;


import exceptions.PersistenciaException;
import exceptions.PrecioInvalidoException;
import exceptions.VehiculoException;
import java.util.List;
import models.Vehiculo;
import models.enums.EstadoVehiculo;
import services.CSVService;
import services.DATService;
import services.GestionVehiculo;
import services.JSONService;

public class VehiculoController {
    private GestionVehiculo gestionVehiculo;

    public VehiculoController() {
        this.gestionVehiculo = new GestionVehiculo();
    }
    /* 
    * @brief Constructor de la clase VehiculoController
    * @param gestionVehiculo: instancia de la clase GestionVehiculo que se utilizará para gestionar los vehículos
    */
    public VehiculoController(GestionVehiculo gestionVehiculo) {
        this.gestionVehiculo = gestionVehiculo;
    }
    
    // ========== CRUD ==========
    /* 
     * @brief Método para agregar un vehículo al sistema
     * @param vehiculo: objeto de tipo Vehiculo a agregar
     */
    public void crear(Vehiculo vehiculo)throws PrecioInvalidoException{
        gestionVehiculo.crear(vehiculo);
    }
    
    /**
     * Lee un vehículo por ID
     * @throws VehiculoException si no existe
     */
    public Vehiculo leer(int id) throws VehiculoException {
        return gestionVehiculo.leer(id);
    }
    
    /**
     * Actualiza un vehículo existente
     * @throws VehiculoException si no existe
     * @throws PrecioInvalidoException si el precio es negativo
     */
    public void actualizar(int id, Vehiculo vehiculoActualizado) throws VehiculoException, PrecioInvalidoException {
        gestionVehiculo.actualizar(id, vehiculoActualizado);
    }

    /* 
     * @brief Método para eliminar un vehículo del sistema
     * @param id: identificador del vehículo a eliminar
     * @throws VehiculoException si no existe 
     */
    public void eliminar(int id) throws VehiculoException{
        gestionVehiculo.eliminar(id);
    }
    
    /**
     * Lista todos los vehículos
     */
    public List<Vehiculo> listarTodos() {
        return gestionVehiculo.getGarage().getVehiculos();
    }

    // ========== ORDENAMIENTO ===========
    /* 
     * @brief Método para ordenar los vehículos según el criterio especificado
     * @param ordenamiento: criterio de ordenamiento ("precio", "anio", "marca", "id")
     */
    public void ordenar(String ordenamiento){
        switch(ordenamiento){
            case "Precio":
                gestionVehiculo.ordenarPorPrecio();
                break;
            case "Año":
                gestionVehiculo.ordenarPorAnio();
                break;
            case "Marca":
                gestionVehiculo.ordenarPorMarca();
                break;
            case "ID":
                gestionVehiculo.ordenarPorId();
                break;
        }
    }
    
    // ============ FILTRADO =============
    /* 
     * @brief Método para filtrar los vehículos por marca
     * @param marca: marca del vehículo a filtrar
     * @throws VehiculoException si no hay vehículos de esa marca
     */
    public List<? extends Vehiculo> filtrarPorMarca(String marca) throws VehiculoException {
        return gestionVehiculo.filtrarPorMarca(marca);
    }
    
    /**
     * Filtra vehículos por estado
     */
    public List<? extends Vehiculo> filtrarPorEstado(EstadoVehiculo estado) {
        return gestionVehiculo.filtrarPorEstado(estado);
    }
    
    // ========== INTERFACES FUNCIONALES ==========
    
    /**
     * Aplica un descuento general a todos los vehículos
     * Usa: Consumer (aplicarDescuento)
     */
    public void aplicarDescuentoGeneral(double porcentaje) {
        gestionVehiculo.aplicarDescuentoGeneral(porcentaje);
    }

    /**
     * Aplica descuento solo a vehículos antiguos
     * Usa: Predicate + Consumer (aplicarCondicional)
     */
    public void aplicarDescuentoAntiguos(int antiguedadMinima, double porcentaje) {
        gestionVehiculo.aplicarDescuentoAntiguos(antiguedadMinima, porcentaje);
    }

    /**
     * Aplica descuento a vehículos de un estado específico
     * Usa: Enum como parámetro + Predicate + Consumer
     */
    public void aplicarDescuentoPorEstado(EstadoVehiculo estado, double porcentaje) {
        gestionVehiculo.aplicarDescuentoPorEstado(estado, porcentaje);
    }
    
    // ========== PERSISTENCIA ==========
    
    /**
     * Guarda en el formato especificado usando Strategy Pattern
     * @param formato "JSON", "CSV" o "DAT"
     * @param ruta Ruta donde guardar el archivo
     * @throws PersistenciaException si hay error al guardar
     */
    public void guardar(String formato, String ruta) throws PersistenciaException {
        switch (formato.toUpperCase()) {
            case "JSON" -> {
                gestionVehiculo.setPersistencia(new JSONService());
                gestionVehiculo.guardar(ruta);
            }
            case "CSV" -> {
                gestionVehiculo.setPersistencia(new CSVService());
                gestionVehiculo.guardar(ruta);
            }
            case "DAT" -> {
                gestionVehiculo.setPersistencia(new DATService());
                gestionVehiculo.guardar(ruta);
            }
            default -> throw new PersistenciaException("Formato desconocido: " + formato);
        }
    }

    /**
     * Carga vehículos desde un archivo (detecta formato por extensión)
     * @param ruta Ruta del archivo a cargar
     * @throws PersistenciaException si hay error al cargar
     */
    public void cargar(String ruta) throws PersistenciaException {
        if (ruta.endsWith(".json")) {
            gestionVehiculo.setPersistencia(new JSONService());
        } else if (ruta.endsWith(".csv")) {
            gestionVehiculo.setPersistencia(new CSVService());
        } else if (ruta.endsWith(".dat")) {
            gestionVehiculo.setPersistencia(new DATService());
        } else {
            throw new PersistenciaException("Formato no soportado. Use .json, .csv o .dat");
        }
        
        gestionVehiculo.cargar(ruta);
    }

    /**
     * Exporta listado a archivo TXT legible
     * @throws PersistenciaException si hay error al exportar
     */
    public void exportarTXT(String ruta) throws PersistenciaException {
        gestionVehiculo.exportarTXT(ruta);
    }
    
    // ========== UTILIDADES ==========
    /**
     * Obtiene el siguiente ID disponible
     */
    public int obtenerSiguienteId() {
        return gestionVehiculo.getGarage().getNextId();
    }

    /**
     * Obtiene el garage (para acceso directo si es necesario)
     */
    public GestionVehiculo getGestion() {
        return gestionVehiculo;
    }
}
