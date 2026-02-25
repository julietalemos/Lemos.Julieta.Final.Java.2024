/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.dialogs;

import models.*;
import models.enums.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;
import javafx.util.StringConverter;

/**
 * Diálogo para crear y editar vehículos
 */
public class VehiculoFormDialog {

    // ========== MÉTODO PARA CREAR NUEVO VEHÍCULO ==========
    
    /**
     * Muestra el formulario para crear un nuevo vehículo
     * @param nextId El ID que se asignará al nuevo vehículo
     * @return Optional con el vehículo creado, o vacío si se cancela
     */
    public static Optional<Vehiculo> mostrar() {
        return mostrarFormulario(-1, null);
    }

    // ========== MÉTODO PARA EDITAR VEHÍCULO EXISTENTE ==========
    
    /**
     * Muestra el formulario para editar un vehículo existente
     * @param vehiculo El vehículo a editar
     * @return Optional con el vehículo actualizado, o vacío si se cancela
     */
    public static Optional<Vehiculo> mostrarEditar(Vehiculo vehiculo) {
        return mostrarFormulario(vehiculo.getId(), vehiculo);
    }

    // ========== MÉTODO PRIVADO QUE HACE TODO EL TRABAJO ==========
    
    private static Optional<Vehiculo> mostrarFormulario(int id, Vehiculo vehiculoExistente) {
        boolean esEdicion = (vehiculoExistente != null);

        Dialog<Vehiculo> dialog = new Dialog<>();
        dialog.setTitle(esEdicion ? "Editar Vehículo" : "Agregar Vehículo");

        ButtonType okBtn = new ButtonType(
            esEdicion ? "Actualizar" : "Agregar", 
            ButtonBar.ButtonData.OK_DONE
        );
        dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

        // ========== CAMPOS DEL FORMULARIO ==========
        
        ChoiceBox<TipoVehiculo> tipo = new ChoiceBox<>(
            //FXCollections.observableArrayList("Auto", "Camion", "Moto")
            FXCollections.observableArrayList(TipoVehiculo.values())
        );
        tipo.setValue(TipoVehiculo.AUTO);
        
        TextField patente = new TextField();
        TextField marca = new TextField();
        TextField modelo = new TextField();
        TextField anio = new TextField();
        TextField precio = new TextField();

        ChoiceBox<TipoCombustible> combustible = new ChoiceBox<>(
            FXCollections.observableArrayList(TipoCombustible.values())
        );
        combustible.setValue(TipoCombustible.NAFTA);

        ChoiceBox<EstadoVehiculo> estado = new ChoiceBox<>(
            FXCollections.observableArrayList(EstadoVehiculo.values())
        );
        estado.setValue(EstadoVehiculo.DISPONIBLE);

        // Campos específicos por tipo
        Label extra1Label = new Label("Puertas:");
        Label extra2Label = new Label("Aire Acon.:");

        TextField extra1 = new TextField();
        
        ChoiceBox<Boolean> extra2Boolean = new ChoiceBox<>(
            FXCollections.observableArrayList(true, false)
        );
        extra2Boolean.setValue(true);
        
        extra2Boolean.setConverter(new StringConverter<Boolean>() {
            @Override
            public String toString(Boolean value) {
                return value ? "Sí" : "No";
            }

            @Override
            public Boolean fromString(String string) {
                return "Sí".equals(string);
            }
        });


        TextField extra2Number = new TextField();
        extra2Number.setVisible(false);
        extra2Number.setManaged(false);

        // ========== PRELLENAR SI ES EDICIÓN ==========
        
        if (esEdicion) {
            tipo.setValue(vehiculoExistente.getTipo());
            tipo.setDisable(true);  // No se puede cambiar el tipo al editar
            
            patente.setText(vehiculoExistente.getPatente());
            marca.setText(vehiculoExistente.getMarca());
            modelo.setText(vehiculoExistente.getModelo());
            anio.setText(String.valueOf(vehiculoExistente.getAnio()));
            precio.setText(String.valueOf(vehiculoExistente.getPrecio()));
            combustible.setValue(vehiculoExistente.getCombustible());
            estado.setValue(vehiculoExistente.getEstado());

            // Prellenar campos específicos según tipo
            if (vehiculoExistente instanceof Auto auto) {
                extra1.setText(String.valueOf(auto.getNumPuertas()));
                extra2Boolean.setValue(auto.getTieneAireAcondicionado());
            } else if (vehiculoExistente instanceof Moto moto) {
                extra1.setText(String.valueOf(moto.getCilindrada()));
                extra2Boolean.setValue(moto.getEsDeportiva());
            } else if (vehiculoExistente instanceof Camion camion) {
                extra1.setText(String.valueOf(camion.getCapacidadCarga()));
                extra2Number.setText(String.valueOf(camion.getNumRuedas()));
                extra2Number.setVisible(true);
                extra2Number.setManaged(true);
                extra2Boolean.setVisible(false);
                extra2Boolean.setManaged(false);
            }
        }

        // ========== LISTENER PARA CAMBIO DE TIPO ==========
        
        tipo.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldVal, newVal) -> {
                switch (newVal) {
                    case AUTO -> {
                        extra1Label.setText("Puertas:");
                        extra2Label.setText("Aire Acon.:");
                        
                        extra2Boolean.setVisible(true);
                        extra2Boolean.setManaged(true);
                        extra2Number.setVisible(false);
                        extra2Number.setManaged(false);
                    }
                    case CAMION -> {
                        extra1Label.setText("Capacidad Carga:");
                        extra2Label.setText("Cantidad Ruedas:");
                        
                        extra2Boolean.setVisible(false);
                        extra2Boolean.setManaged(false);
                        extra2Number.setVisible(true);
                        extra2Number.setManaged(true);
                    }
                    case MOTO -> {
                        extra1Label.setText("Cilindrada:");
                        extra2Label.setText("Es Deportiva:");
                        
                        extra2Boolean.setVisible(true);
                        extra2Boolean.setManaged(true);
                        extra2Number.setVisible(false);
                        extra2Number.setManaged(false);
                    }
                }
            });

        // ========== LAYOUT ==========
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        int row = 0;
        grid.addRow(row++, new Label("Tipo:"), tipo);
        grid.addRow(row++, new Label("Patente:"), patente);
        grid.addRow(row++, new Label("Marca:"), marca);
        grid.addRow(row++, new Label("Modelo:"), modelo);
        grid.addRow(row++, new Label("Año:"), anio);
        grid.addRow(row++, new Label("Combustible:"), combustible);
        grid.addRow(row++, new Label("Estado:"), estado);
        grid.addRow(row++, new Label("Precio:"), precio);
        grid.addRow(row++, extra1Label, extra1);
        grid.addRow(row, extra2Label, extra2Boolean);
        grid.add(extra2Number, 1, row);

        dialog.getDialogPane().setContent(grid);

        // ========== VALIDACIÓN Y CONVERSIÓN ==========
        
        dialog.setResultConverter(btn -> {
            
            if (btn == okBtn) {
                try {
                    // Parsear valores comunes
                    int anioVal = Integer.parseInt(anio.getText());
                    double precioVal = Double.parseDouble(precio.getText());
                    int extra1Val = Integer.parseInt(extra1.getText());
                    // Crear vehículo según tipo
                    return switch (tipo.getValue()) {
                        case AUTO -> new Auto(
                            id,
                            patente.getText(),
                            marca.getText(),
                            modelo.getText(),
                            anioVal,
                            precioVal,
                            combustible.getValue(),
                            estado.getValue(),
                            extra1Val,
                            extra2Boolean.getValue()
                        );

                        case CAMION -> new Camion(
                            id,
                            patente.getText(),
                            marca.getText(),
                            modelo.getText(),
                            anioVal,
                            precioVal,
                            combustible.getValue(),
                            estado.getValue(),
                            extra1Val,
                            Integer.parseInt(extra2Number.getText())
                        );

                        case MOTO -> new Moto(
                            id,
                            patente.getText(),
                            marca.getText(),
                            modelo.getText(),
                            anioVal,
                            precioVal,
                            combustible.getValue(),
                            estado.getValue(),
                            extra1Val,
                            extra2Boolean.getValue()
                        );

                        default -> null;
                    };
                    
                } catch (NumberFormatException e) {
                    // Mostrar error si los datos son inválidos
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de Validación");
                    alert.setHeaderText("Datos inválidos");
                    alert.setContentText("Por favor ingrese valores numéricos válidos");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }
}