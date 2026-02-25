
package models;

import java.time.Year;

import models.enums.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class Vehiculo implements Comparable<Vehiculo>, Serializable {
    protected int id;
    protected String patente;
    protected String marca;
    protected String modelo;
    protected int anio;
    protected TipoVehiculo tipo;
    protected TipoCombustible combustible;
    protected EstadoVehiculo estado;
    protected double precio;

    /* 
     * @brief Constructor de la clase Vehiculo
     * @param id: identificador del vehículo
     * @param patente: patente del vehículo
     * @param marca: marca del vehículo
     * @param modelo: modelo del vehículo
     * @param anio: año del vehículo
     * @param tipo: tipo de vehículo (AUTO, MOTO, CAMION)
     * @param combustible: tipo de combustible del vehículo (GASOLINA, DIESEL, ELECTRICO)
     * @param estado: estado del vehículo (DISPONIBLE, VENDIDO, RESERVADO)
     * @param precio: precio del vehículo
     */
    public Vehiculo(int id, String patente, String marca, String modelo, int anio, TipoVehiculo tipo, TipoCombustible combustible, EstadoVehiculo estado, double precio) {
        this.id = id;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.tipo = tipo;
        this.combustible = combustible;
        this.estado = estado;
        this.precio = precio;
    }

    /* 
     * @brief Método para obtener el identificador del vehículo
     * @return identificador del vehículo
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /* 
     * @brief Método para obtener la patente del vehículo
     * @return patente del vehículo
     */
    public String getPatente() {
        return patente;
    }

    /* 
     * @brief Método para obtener la marca del vehículo
     * @return marca del vehículo
     */
    public String getMarca() {
        return marca;
    }

    /* 
     * @brief Método para obtener el modelo del vehículo
     * @return modelo del vehículo
     */
    public String getModelo() {
        return modelo;
    }

    /* 
     * @brief Método para obtener el año del vehículo
     * @return año del vehículo
     */
    public int getAnio() {
        return anio;
    }

    /* 
     * @brief Método para obtener el tipo del vehículo
     * @return tipo del vehículo
     */
    public TipoVehiculo getTipo() {
        return tipo;
    }
    
    /* 
     * @brief Método para obtener el tipo de combustible del vehículo
     * @return tipo de combustible del vehículo
     */
    public TipoCombustible getCombustible() {
        return combustible;
    }

    /* 
     * @brief Método para obtener el estado del vehículo
     * @return estado del vehículo
     */
    public EstadoVehiculo getEstado() {
        return estado;
    }

    /* 
     * @brief Método para obtener el precio del vehículo
     * @return precio del vehículo
     */
    public double getPrecio() {
        return precio;
    }

    /* 
     * @brief Método para establecer el precio del vehículo
     * @param precio: nuevo precio del vehículo
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /* 
     * @brief Método para establecer el estado del vehículo
     * @param estado: nuevo estado del vehículo
     */
    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    /* 
     * @brief Método para obtener la edad del vehículo
     * @return edad del vehículo
     */
    public int getEdad() {
        return Year.now().getValue() - this.anio;
    }

    @Override
    public int compareTo(Vehiculo other){
        return Integer.compare(this.getId(), other.getId());
    }

    /* 
     * @brief Método abstracto para obtener el texto del vehículo
     * @return texto del vehículo
     */
    //public abstract String getTXT();
    
    /* 
     * @brief Método abstracto para obtener el CSV del vehículo
     * @return CSV del vehículo
     */
    //public abstract String getCSV();
    
    private static String mostrar(Vehiculo vehiculo){
        StringBuilder sb = new StringBuilder();
        sb.append("\nId: ").append(vehiculo.getId());
        sb.append("\nPatente: ").append(vehiculo.getPatente());
        sb.append("\nMarca: ").append(vehiculo.getMarca());
        sb.append("\nModelo: ").append(vehiculo.getModelo());
        sb.append("\nAnio: ").append(vehiculo.getAnio());
        sb.append("\nEstado: ").append(vehiculo.getEstado());
        sb.append("\nCombustible: ").append(vehiculo.getCombustible());
        sb.append("\nPrecio: ").append(vehiculo.getPrecio());
        return sb.toString();        
    }
    
    public static boolean sonIguales(Vehiculo v1, Vehiculo v2){
        return Objects.equals(v1.getId(), v2.getId());
        //return v1.getId().equals(v2.getId());
    }
    
    @Override
    public String toString() {
        return Vehiculo.mostrar(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Vehiculo vehiculo = (Vehiculo) obj;
        return Vehiculo.sonIguales(vehiculo, this);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
    
}