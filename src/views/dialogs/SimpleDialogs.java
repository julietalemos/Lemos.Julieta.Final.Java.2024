/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.dialogs;


import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

/**
 * Diálogos simples reutilizables
 */
public class SimpleDialogs {

    // ========== MENSAJES ==========
    
    public static void mostrarExito(String mensaje) {
        mostrarMensaje("Éxito", mensaje, AlertType.INFORMATION);
    }

    public static void mostrarError(String mensaje) {
        mostrarMensaje("Error", mensaje, AlertType.ERROR);
    }

    public static void mostrarAdvertencia(String mensaje) {
        mostrarMensaje("Advertencia", mensaje, AlertType.WARNING);
    }

    public static void mostrarInfo(String mensaje) {
        mostrarMensaje("Información", mensaje, AlertType.INFORMATION);
    }

    private static void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    // ========== CONFIRMACIÓN ==========
    
    public static boolean confirmar(String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        
        return alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .isPresent();
    }

    // ========== INPUT DE TEXTO ==========
    
    public static Optional<String> ingresarTexto(String titulo, String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titulo);
        dialog.setHeaderText(mensaje);
        dialog.setContentText("Ingrese el valor:");
        
        return dialog.showAndWait();
    }

    // ========== INPUT DE PORCENTAJE ==========
    
    public static Optional<Double> ingresarPorcentaje(String titulo) {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle(titulo);
        dialog.setHeaderText("Ingrese el porcentaje:");
        dialog.setContentText("Porcentaje:");
        
        return dialog.showAndWait()
            .flatMap(texto -> {
                try {
                    return Optional.of(Double.parseDouble(texto));
                } catch (NumberFormatException e) {
                    mostrarError("Ingrese un número válido");
                    return Optional.empty();
                }
            });
    }

    // ========== SELECCIONAR ORDENAMIENTO ==========
    
    public static Optional<String> seleccionarOrdenamiento() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(
            "Precio",
            "Precio", "Año", "Marca", "ID"
        );
        
        dialog.setTitle("Ordenar Vehículos");
        dialog.setHeaderText("Seleccione el criterio de ordenamiento:");
        dialog.setContentText("Ordenar por:");
        
        return dialog.showAndWait();
    }
    
    public static Optional<String> seleccionarFiltro() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(
            "Marca",
            "Marca", "Estado"
        );
        
        dialog.setTitle("Filtrar Vehículos");
        dialog.setHeaderText("Seleccione el criterio de filtro:");
        dialog.setContentText("Filtrar por:");
        
        return dialog.showAndWait();
    }
}