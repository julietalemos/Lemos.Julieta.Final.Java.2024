/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.enums.*;
import interfaces.Mantenible;

import java.time.Year;

public class Auto extends Vehiculo implements Mantenible{
    private int numPuertas;
    private boolean tieneAireAcondicionado;

    /* 
     * @brief Constructor de la clase Auto
     * @param id: identificador del auto
     * @param patente: patente del auto
     * @param marca: marca del auto
     * @param modelo: modelo del auto
     */
    public Auto(int id, String patente, String marca, String modelo){
        super(id, patente, marca, modelo, Year.now().getValue(), TipoVehiculo.AUTO, TipoCombustible.NAFTA, EstadoVehiculo.NO_DISPONIBLE, 0);
        this.numPuertas = 4;
        this.tieneAireAcondicionado = true;
    }

    /* 
     * @brief Constructor de la clase Auto con parámetros adicionales
     * @param id: identificador del auto
     * @param patente: patente del auto
     * @param marca: marca del auto
     * @param modelo: modelo del auto
     * @param precio: precio del auto
     * @param numPuertas: número de puertas del auto
     * @param tieneAireAcondicionado: si el auto tiene aire acondicionado o no
     */
    public Auto(int id, String patente, String marca, String modelo, double precio, int numPuertas, boolean tieneAireAcondicionado) {
        super(id, patente, marca, modelo, Year.now().getValue(), TipoVehiculo.AUTO, TipoCombustible.NAFTA, EstadoVehiculo.NO_DISPONIBLE, precio);
        this.numPuertas = numPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    /* 
     * @brief Constructor de la clase Auto con todos los parámetros
     * @param id: identificador del auto
     * @param patente: patente del auto
     * @param marca: marca del auto
     * @param modelo: modelo del auto
     * @param anio: año del auto
     * @param precio: precio del auto
     * @param tipoCombustible: tipo de combustible del auto
     * @param estado: estado del auto
     * @param numPuertas: número de puertas del auto
     * @param tieneAireAcondicionado: si el auto tiene aire acondicionado o no
     */
    public Auto(int id, String patente, String marca, String modelo, int anio, double precio, TipoCombustible tipoCombustible, EstadoVehiculo estado, int numPuertas, boolean tieneAireAcondicionado) {
        super(id, patente, marca, modelo, anio, TipoVehiculo.AUTO, tipoCombustible, estado, precio);
        this.numPuertas = numPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    /* 
     * @brief Método para obtener el número de puertas del auto
     * @return número de puertas del auto
     */
    public int getNumPuertas() {
        return numPuertas;
    }

    /* 
     * @brief Método para obtener si el auto tiene aire acondicionado o no
     * @return true si el auto tiene aire acondicionado, false en caso contrario
     */
    public boolean getTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }
    
    @Override
    public boolean instalarAireAcondicionado() {
        if(!tieneAireAcondicionado){
            this.tieneAireAcondicionado = true;            
        }
        return tieneAireAcondicionado;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nAUTO");
        sb.append(super.toString());
        sb.append("\nNumero de puertas: ").append(this.getNumPuertas());
        sb.append("\nTiene Aire Acond.: ").append(this.getTieneAireAcondicionado() ? "Si" : "No");
        return sb.toString();

    }   
}