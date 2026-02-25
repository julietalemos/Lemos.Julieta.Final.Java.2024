/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iterator;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author julie
 */
public class VehiculoIterator<T> implements Iterator<T>{
    private final List<T> lista;
    private int posicion = 0;

    public VehiculoIterator(List<T> lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return posicion < lista.size();
    }

    @Override
    public T next() {
        return lista.get(posicion++);
    }

}
