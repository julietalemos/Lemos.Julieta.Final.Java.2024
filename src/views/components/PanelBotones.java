/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.components;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.*;

/**
 * Panel lateral con todos los botones
 * Usa callbacks (Runnable) para delegar las acciones a MainView
 * @author julie
 */
public class PanelBotones {
    private VBox root;

    public PanelBotones() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #bdc3c7; -fx-border-width: 0 0 0 2;");
        root.setPrefWidth(200);
    }
    
    // ========== SETTERS DE EVENTOS (Callbacks) ==========

    public void setOnAgregar(Runnable action) {
        Button btn = crearBoton("➕ Agregar", "#27ae60", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnActualizar(Runnable action) {
        Button btn = crearBoton("✏️ Actualizar", "#3498db", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnEliminar(Runnable action) {
        Button btn = crearBoton("🗑️ Eliminar", "#e74c3c", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnOrdenar(Runnable action) {
        agregarSeparador();
        Button btn = crearBoton("↕️ Ordenar", "#9b59b6", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnFiltrarPorMarca(Runnable action) {
        Button btn = crearBoton("🔍 Marca", "#f39c12", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }
    
    public void setOnFiltrarPorEstado(Runnable action) {
        Button btn = crearBoton("🔍 Estado", "#f39c12", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnMostrarTodos(Runnable action) {
        Button btn = crearBoton("📋 Ver Todos", "#34495e", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnDescuentoGeneral(Runnable action) {
        agregarSeparador();
        Button btn = crearBoton("💰 Descuento 10%", "#e67e22", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnDescuentoAntiguos(Runnable action) {
        Button btn = crearBoton("📉 Desc. Antiguos", "#d35400", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnGuardar(Runnable action) {
        agregarSeparador();
        Button btn = crearBoton("💾 Guardar", "#16a085", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnCargar(Runnable action) {
        Button btn = crearBoton("📂 Cargar", "#1abc9c", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    public void setOnExportar(Runnable action) {
        Button btn = crearBoton("📄 Exportar TXT", "#95a5a6", "#ffffff");
        btn.setOnAction(e -> action.run());
        root.getChildren().add(btn);
    }

    // ========== MÉTODOS PRIVADOS ==========

    private Button crearBoton(String texto, String colorFondo, String colorTexto) {
        Button btn = new Button(texto);
        btn.setPrefWidth(160);
        btn.setPrefHeight(40);
        aplicarEstilo(btn, colorFondo, colorTexto);
        return btn;
    }

    private void aplicarEstilo(Button btn, String colorFondo, String colorTexto) {
        String estiloBase = String.format(
            "-fx-background-color: %s; -fx-text-fill: %s; " +
            "-fx-font-size: 14px; -fx-font-weight: bold; " +
            "-fx-background-radius: 5; -fx-cursor: hand;",
            colorFondo, colorTexto);
        
        btn.setStyle(estiloBase);
        
        btn.setOnMouseEntered(e -> 
            btn.setStyle(estiloBase + "-fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
        btn.setOnMouseExited(e -> btn.setStyle(estiloBase));
    }

    private void agregarSeparador() {
        Separator sep = new Separator();
        sep.setPadding(new Insets(10, 0, 10, 0));
        root.getChildren().add(sep);
    }

    public VBox getRoot() {
        return root;
    }
}
