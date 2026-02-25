/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author julie
 */
import models.enums.EstadoVehiculo;
import models.enums.TipoCombustible;
import models.enums.TipoVehiculo;

import java.time.Year;

public class Camion extends Vehiculo {
    private int capacidadCarga;
    private int numRuedas;

    /* 
     * @brief Constructor de la clase Camion
     * @param id: identificador del camión
     * @param patente: patente del camión
     * @param marca: marca del camión
     * @param modelo: modelo del camión
     */
    public Camion(int id, String patente, String marca, String modelo){
        super(id, patente, marca, modelo, Year.now().getValue(), TipoVehiculo.CAMION, TipoCombustible.NAFTA, EstadoVehiculo.NO_DISPONIBLE, 0);
        this.capacidadCarga = 1000;
        this.numRuedas = 6;

    }

    /* 
     * @brief Constructor de la clase Camion con parámetros adicionales
     * @param id: identificador del camión
     * @param patente: patente del camión
     * @param marca: marca del camión
     * @param modelo: modelo del camión
     * @param precio: precio del camión
     * @param capacidadCarga: capacidad de carga del camión
     * @param numRuedas: número de ruedas del camión
     */
    public Camion(int id, String patente, String marca, String modelo, double precio, int capacidadCarga, int numRuedas) {
        super(id, patente, marca, modelo, Year.now().getValue(), TipoVehiculo.CAMION, TipoCombustible.NAFTA, EstadoVehiculo.NO_DISPONIBLE, precio);
        this.capacidadCarga = capacidadCarga;
        this.numRuedas = numRuedas;
    }

    /* 
     * @brief Constructor de la clase Camion con todos los parámetros
     * @param id: identificador del camión
     * @param patente: patente del camión
     * @param marca: marca del camión
     * @param modelo: modelo del camión
     * @param anio: año del camión
     * @param precio: precio del camión
     * @param tipoCombustible: tipo de combustible del camión
     * @param estado: estado del camión
     * @param capacidadCarga: capacidad de carga del camión
     * @param numRuedas: número de ruedas del camión
     */
    public Camion(int id, String patente, String marca, String modelo, int anio, double precio, TipoCombustible tipoCombustible, EstadoVehiculo estado, int capacidadCarga, int numRuedas) {
        super(id, patente, marca, modelo, anio, TipoVehiculo.CAMION, tipoCombustible, estado, precio);
        this.capacidadCarga = capacidadCarga;
        this.numRuedas = numRuedas;
    }

    /* 
     * @brief Método para obtener la capacidad de carga del camión
     * @return capacidad de carga del camión
     */
    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    /* 
     * @brief Método para obtener el número de ruedas del camión
     * @return número de ruedas del camión
     */
    public int getNumRuedas() {
        return numRuedas;
    }

    /* 
     * @brief Método para verificar si hay capacidad restante en el camión
     * @param cargaActual: carga actual del camión
     * @return true si hay capacidad restante, false en caso contrario
     */
    public boolean capacidadRestante(int cargaActual) {
        return cargaActual < capacidadCarga;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nCAMION");
        sb.append(super.toString());
        sb.append("\nCapacidad de carga: ").append(this.getCapacidadCarga());
        sb.append("\nNumero de ruedas: ").append(this.getNumRuedas());
        return sb.toString();       
    }

}