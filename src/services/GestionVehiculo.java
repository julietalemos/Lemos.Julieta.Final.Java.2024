package services;

import comparators.*;
import models.*;
import exceptions.*;
import interfaces.Persistencia;

import java.util.List;
import models.enums.EstadoVehiculo;

public class GestionVehiculo {
    private Garage garage;
    private Persistencia persistenciaActual;

    /* 
     * @brief Constructor de la clase GestionVehiculo
     */
    public GestionVehiculo() {
        this.garage = new Garage();
        this.persistenciaActual = new JSONService();
    }

    /* 
    * @brief Constructor de la clase GestionVehiculo
    * @param garage: instancia de la clase Garage que se utilizará para gestionar los vehículos
    */
    public GestionVehiculo(Garage garage) {
        this.garage = garage;
    }

    /* 
    * @brief Método para obtener el garage
    * @return instancia del garage
    */
    public Garage getGarage() {
        return garage;
    }
    
    /* 
    * @brief Método para crear un vehículo en el garage
    * @param vehiculo: vehículo que se desea crear
    * @throws PrecioInvalidoException si el precio del vehículo es negativo
    */
    public void crear(Vehiculo vehiculo) throws PrecioInvalidoException{
        if (vehiculo.getPrecio() < 0) {
            throw new PrecioInvalidoException("El precio no puede ser negativo");
        }
        validarVehiculo(vehiculo);
        this.garage.crear(vehiculo);
    }

    /* 
    * @brief Método para leer un vehículo del garage
    * @param id: id del vehículo que se desea leer
    * @return vehículo con el id especificado o null si no se encuentra
    * @throws VehiculoNoEncontradoException si el vehículo con el id especificado no se encuentra en el garage
    */
    public Vehiculo leer(int id) throws VehiculoException {
        Vehiculo vehiculo = this.garage.leer(id);
        if (vehiculo == null) {
            throw new VehiculoException("Vehículo con ID " + id + " no encontrado");
        }
        return vehiculo;
    }

    /* 
    * @brief Método para actualizar un vehículo en el garage
    * @param id: id del vehículo que se desea actualizar
    * @param vehiculo: nuevo vehículo con los datos actualizados
    * @throws VehiculoNoEncontradoException si el vehículo con el id especificado no se encuentra en el garage
    * @throws PrecioInvalidoException si el precio del nuevo vehículo es negativo
    */
    public void actualizar(int id, Vehiculo vehiculo) throws VehiculoException, PrecioInvalidoException{
        Vehiculo objetivo = this.garage.leer(id);
        if (objetivo == null) {
            throw new VehiculoException("Vehículo con ID " + id + " no encontrado");
        }
        validarVehiculo(vehiculo);
        this.garage.actualizar(id, vehiculo);
    }

    /* 
    * @brief Método para eliminar un vehículo del garage
    * @param id: id del vehículo que se desea eliminar
    * @throws VehiculoNoEncontradoException si el vehículo con el id especificado no se encuentra en el garage
    */
    public void eliminar(int id) throws VehiculoException{
        Vehiculo vehiculo = this.garage.leer(id);
        if (vehiculo == null) {
            throw new VehiculoException("Vehículo con ID " + id + " no encontrado");
        }
        this.garage.eliminar(id);
    }

    /* 
    * @brief Método para filtrar vehículos por marca
    * @param marca: marca por la que se desea filtrar
    * @return lista de vehículos que coinciden con la marca especificada
    * @throws VehiculoException si no se encuentran vehículos de la marca especificada
    */
    public List<? extends Vehiculo> filtrarPorMarca(String marca) throws VehiculoException {
        List<? extends Vehiculo> vehiculosFiltrados = this.garage.filtrarPorMarca(marca);
        if (vehiculosFiltrados.isEmpty()) {
            throw new VehiculoException("No se encontraron vehículos de la marca " + marca);
        }
        return vehiculosFiltrados;
    }
    
    public List<? extends Vehiculo> filtrarPorEstado(EstadoVehiculo estado) throws VehiculoException {
        List<? extends Vehiculo> vehiculosFiltrados = this.garage.filtrarPorEstado(estado);
        if (vehiculosFiltrados.isEmpty()) {
            throw new VehiculoException("No se encontraron vehículos de la marca " + estado);
        }
        return vehiculosFiltrados;
    }

    /* 
    * @brief Método para ordenar los vehículos del garage por precio
    */
    public void ordenarPorPrecio(){
        this.garage.ordenar(new PrecioComparator());
    }

    /* 
    * @brief Método para ordenar los vehículos del garage por año
    */
    public void ordenarPorAnio(){
        this.garage.ordenar(new AnioComparator());
    }

    /* 
    * @brief Método para ordenar los vehículos del garage por marca
    */
    public void ordenarPorMarca(){
        this.garage.ordenar(new MarcaComparator());
    }
    
    /* 
    * @brief Método para ordenar los vehículos del garage por ID
    */
    public void ordenarPorId(){
        this.garage.ordenar(new IdComparator());
    }
    
    /**
     * Aplica un descuento general a TODOS los vehículos
     * REQUISITO: Usa Consumer (interfaces funcionales)
     */
    public void aplicarDescuentoGeneral(double porcentaje) {
        garage.aplicarDescuento(v -> 
            v.setPrecio(v.getPrecio() * (1 - porcentaje / 100))
        );
    }

    /**
     * Aplica descuento solo a vehículos antiguos
     * REQUISITO: Usa Predicate + Consumer (interfaces funcionales)
     */
    public void aplicarDescuentoAntiguos(int edadMinima, double porcentaje) {
        garage.aplicarCondicional(
            v -> v.getEdad() >= edadMinima,  // Predicate
            v -> v.setPrecio(v.getPrecio() * (1 - porcentaje / 100))  // Consumer
        );
    }

    /**
     * Aplica descuento a vehículos de un estado específico
     * REQUISITO: Usa Enum como parámetro + Predicate + Consumer
     */
    public void aplicarDescuentoPorEstado(EstadoVehiculo estado, double porcentaje) {
        garage.aplicarCondicional(
            v -> v.getEstado() == estado,  // Predicate con enum
            v -> v.setPrecio(v.getPrecio() * (1 - porcentaje / 100))  // Consumer
        );
    }

    /**
     * Lista todos los vehículos
     */
    public List<Vehiculo> listarTodos() {
        return garage.getVehiculos();
    }
    
    // =============-------- PERSISTENCIA --------==============
    
    /*
     * Permite cambiar dinámicamente la estrategia de persistencia
     * @param persistencia Puede ser PersistenciaJSON, PersistenciaCSV o PersistenciaDAT
     */
    public void setPersistencia(Persistencia persistencia) {
        this.persistenciaActual = persistencia;
    }
    
    /**
     * Guarda usando la estrategia configurada
     */
    public void guardar(String ruta) throws PersistenciaException {
        List<Vehiculo> vehiculos = garage.getVehiculos();
        this.persistenciaActual.guardar(vehiculos, ruta);
    }

    /**
     * Carga usando la estrategia configurada
     */
    public void cargar(String ruta) throws PersistenciaException {
        List<Vehiculo> vehiculos = this.persistenciaActual.cargar(ruta);
        
        // Cargar los vehículos al garage
        for (Vehiculo v : vehiculos) {
            try {
                garage.crear(v);
            } catch (VehiculoException e) {
                System.err.println("Error al cargar vehículo: " + e.getMessage());
            }
        }
    }
    
    public void exportarTXT(String path){
        this.garage.exportarTXT(path);
    }
    
    // ========== VALIDACIONES ===========
    
    private void validarVehiculo(Vehiculo vehiculo) throws VehiculoException, PrecioInvalidoException{
        if (vehiculo == null) {
            throw new VehiculoException("Vehículo inexistente");
        }
        if (vehiculo.getAnio() < 1980 || vehiculo.getAnio() > 2026) {
            throw new VehiculoException("El año debe estar entre 1980 y 2026");
        }
        if (vehiculo.getPrecio() < 0) {
            throw new PrecioInvalidoException("El precio no puede ser negativo");
        }
        if (vehiculo instanceof Auto) {
            Auto auto = (Auto) vehiculo;
            if (auto.getNumPuertas() < 2 || auto.getNumPuertas() > 5) {
                throw new VehiculoException("Un auto debe tener entre 2 y 5 puertas");
            }
        }
        if (vehiculo instanceof Camion) {
            Camion camion = (Camion) vehiculo;
            if (camion.getNumRuedas() < 4 || camion.getNumRuedas() > 10) {
                throw new VehiculoException("Un camión debe tener al menos 6 ruedas");
            }
        }
    }
    
    
}