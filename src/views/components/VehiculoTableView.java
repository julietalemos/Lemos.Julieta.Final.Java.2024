/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.components;

import models.Vehiculo;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.Optional;

/**
 * Componente que encapsula la tabla de vehículos
 * @author julie
 */
public class VehiculoTableView {
    private TableView<Vehiculo> table;
    private VBox root;
    
    public VehiculoTableView() {
        inicializarTabla();
        configurarColumnas();
        configurarEstilos();
    }

    private void inicializarTabla() {
        table = new TableView<>();
        root = new VBox(table);
    }

    private void configurarColumnas() {
        TableColumn<Vehiculo, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(50);

        TableColumn<Vehiculo, String> colPatente = new TableColumn<>("Patente");
        colPatente.setCellValueFactory(new PropertyValueFactory<>("patente"));
        colPatente.setPrefWidth(100);

        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colMarca.setPrefWidth(120);

        TableColumn<Vehiculo, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colModelo.setPrefWidth(120);

        TableColumn<Vehiculo, Integer> colAnio = new TableColumn<>("Año");
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colAnio.setPrefWidth(70);

        TableColumn<Vehiculo, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setPrefWidth(100);
        colPrecio.setCellFactory(col -> new TableCell<Vehiculo, Double>() {
            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                setText(empty || precio == null ? null : String.format("$%.2f", precio));
            }
        });

        TableColumn<Vehiculo, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getTipo().toString()));
        colTipo.setPrefWidth(80);

        TableColumn<Vehiculo, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getEstado().toString()));
        colEstado.setPrefWidth(120);

        table.getColumns().addAll(colId, colPatente, colMarca, colModelo, 
            colAnio, colPrecio, colTipo, colEstado);
    }

    private void configurarEstilos() {
        table.setStyle("-fx-background-color: #ffffff;");
        
        Label placeholder = new Label("No hay vehículos en el garage");
        placeholder.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 14px;");
        table.setPlaceholder(placeholder);
    }

    public void actualizarVehiculos(List<Vehiculo> vehiculos) {
        table.setItems(FXCollections.observableArrayList(vehiculos));
        table.refresh();
    }

    public Optional<Vehiculo> getVehiculoSeleccionado() {
        return Optional.ofNullable(table.getSelectionModel().getSelectedItem());
    }

    public VBox getRoot() {
        return root;
    }
}
