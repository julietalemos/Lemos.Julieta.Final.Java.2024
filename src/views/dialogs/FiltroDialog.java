/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.dialogs;


import models.enums.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;

/**
 * Diálogos para filtrado de vehículos
 */
public class FiltroDialog {

    // ========== FILTRO POR MARCA ==========
    
    public static Optional<String> filtrarPorMarca() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Filtrar por Marca");
        dialog.setHeaderText("Ingrese la marca:");
        dialog.setContentText("Marca:");
        
        return dialog.showAndWait();
    }

    // ========== FILTRO POR ESTADO ==========
    
    public static Optional<EstadoVehiculo> filtrarPorEstado() {
        ChoiceDialog<EstadoVehiculo> dialog = new ChoiceDialog<>(
            EstadoVehiculo.DISPONIBLE,
            EstadoVehiculo.values()
        );
        
        dialog.setTitle("Filtrar por Estado");
        dialog.setHeaderText("Seleccione el estado:");
        dialog.setContentText("Estado:");
        
        return dialog.showAndWait();
    }

    // ========== DESCUENTO A ANTIGUOS ==========
    
    /**
     * Record para devolver múltiples valores
     */
    public record ParametrosDescuento(int anios, double descuento) {}

    public static Optional<ParametrosDescuento> mostrarDescuentoAntiguos() {
        Dialog<ParametrosDescuento> dialog = new Dialog<>();
        dialog.setTitle("Descuento a Vehículos Antiguos");
        dialog.setHeaderText("Configure el descuento:");

        ButtonType okBtn = new ButtonType("Aplicar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField anios = new TextField("5");
        TextField descuento = new TextField("20");

        grid.addRow(0, new Label("Antiguedad mínima:"), anios);
        grid.addRow(1, new Label("% Descuento:"), descuento);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(btn -> {
            if (btn == okBtn) {
                try {
                    int aniosVal = Integer.parseInt(anios.getText());
                    double descuentoVal = Double.parseDouble(descuento.getText());
                    return new ParametrosDescuento(aniosVal, descuentoVal);
                } catch (NumberFormatException e) {
                    SimpleDialogs.mostrarError("Valores inválidos");
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }
}