/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comparators;

import java.util.Comparator;
import models.Vehiculo;
/**
 *
 * @author julie
 */

public class MarcaComparator implements Comparator<Vehiculo> {
    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        return v1.getMarca().compareToIgnoreCase(v2.getMarca());
    }
}