/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.dialogs;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.Optional;

/**
 *
 * @author julie
 */
public class PersistenciaDialog {
    public record ResultadoGuardar(String formato, String ruta) {}

    public static Optional<ResultadoGuardar> mostrarGuardar() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Guardar Datos");
        dialog.setHeaderText("Seleccione el formato:");
        
        ButtonType btnJSON = new ButtonType("JSON");
        ButtonType btnCSV = new ButtonType("CSV");
        ButtonType btnDAT = new ButtonType("DAT");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        dialog.getButtonTypes().setAll(btnJSON, btnCSV, btnDAT, btnCancelar);
        
        return dialog.showAndWait()
            .filter(tipo -> tipo != btnCancelar)
            .flatMap(tipo -> {
                FileChooser fileChooser = new FileChooser();
                String formato;
                
                if (tipo == btnJSON) {
                    fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("JSON", "*.json"));
                    formato = "JSON";
                } else if (tipo == btnCSV) {
                    fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("CSV", "*.csv"));
                    formato = "CSV";
                } else {
                    fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("DAT", "*.dat"));
                    formato = "DAT";
                }
                
                File file = fileChooser.showSaveDialog(null);
                return file != null 
                    ? Optional.of(new ResultadoGuardar(formato, file.getAbsolutePath()))
                    : Optional.empty();
            });
    }

    public static Optional<String> mostrarCargar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Archivo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Todos", "*.json", "*.csv", "*.dat"),
            new FileChooser.ExtensionFilter("JSON", "*.json"),
            new FileChooser.ExtensionFilter("CSV", "*.csv"),
            new FileChooser.ExtensionFilter("DAT", "*.dat")
        );
        
        File file = fileChooser.showOpenDialog(null);
        return file != null ? Optional.of(file.getAbsolutePath()) : Optional.empty();
    }

    public static Optional<String> seleccionarArchivoTXT() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar a TXT");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Texto", "*.txt"));
        
        File file = fileChooser.showSaveDialog(null);
        return file != null ? Optional.of(file.getAbsolutePath()) : Optional.empty();
    }
}
