/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author julie
 */
import models.enums.*;

import java.time.Year;

public class Moto extends Vehiculo {
    private int cilindrada;
    private boolean esDeportiva;

    /* 
     * @brief Constructor de la clase Moto
     * @param id: identificador de la moto
     * @param patente: patente de la moto
     * @param marca: marca de la moto
     * @param modelo: modelo de la moto
     */
    public Moto(int id, String patente, String marca, String modelo){
        super(id, patente, marca, modelo, Year.now().getValue(), TipoVehiculo.MOTO, TipoCombustible.NAFTA, EstadoVehiculo.NO_DISPONIBLE, 0);
        this.cilindrada = 150;
        this.esDeportiva = false;
    }

    /* 
     * @brief Constructor de la clase Moto con parámetros adicionales
     * @param id: identificador de la moto
     * @param patente: patente de la moto
     * @param marca: marca de la moto
     * @param modelo: modelo de la moto
     * @param precio: precio de la moto
     * @param cilindrada: cilindrada de la moto
     * @param esDeportiva: indica si es una moto deportiva o no
     */
    public Moto(int id, String patente, String marca, String modelo, double precio, int cilindrada, boolean esDeportiva) {
        super(id, patente, marca, modelo, Year.now().getValue(), TipoVehiculo.MOTO, TipoCombustible.NAFTA, EstadoVehiculo.NO_DISPONIBLE, precio);
        this.cilindrada = cilindrada;
        this.esDeportiva = esDeportiva;
    }

    /* 
     * @brief Constructor de la clase Moto con todos los parámetros
     * @param id: identificador de la moto
     * @param patente: patente de la moto
     * @param marca: marca de la moto
     * @param modelo: modelo de la moto
     * @param anio: año de la moto
     * @param precio: precio de la moto
     * @param tipoCombustible: tipo de combustible de la moto
     * @param estado: estado de la moto
     * @param cilindrada: cilindrada de la moto
     * @param esDeportiva: indica si es una moto deportiva o no
     */
    public Moto(int id, String patente, String marca, String modelo, int anio, double precio, TipoCombustible tipoCombustible, EstadoVehiculo estado, int cilindrada, boolean esDeportiva) {
        super(id, patente, marca, modelo, anio, TipoVehiculo.MOTO, tipoCombustible, estado, precio);
        this.cilindrada = cilindrada;
        this.esDeportiva = esDeportiva;
    }

    /* 
     * @brief Método para obtener la cilindrada de la moto
     * @return cilindrada de la moto
     */
    public int getCilindrada() {
        return cilindrada;
    }

    /* 
     * @brief Método para obtener si la moto es deportiva o no
     * @return true si es deportiva, false en caso contrario
     */
    public boolean getEsDeportiva() {
        return esDeportiva;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nMOTO");
        sb.append(super.toString());
        sb.append("\nCilindrada: ").append(this.getCilindrada());
        sb.append("\nEs deportiva: ").append(this.getEsDeportiva() ? "Si" : "No");
        return sb.toString();       
    }
}
