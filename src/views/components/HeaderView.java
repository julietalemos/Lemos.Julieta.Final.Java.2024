/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.geometry.*;

/**
 *
 * @author julie
 */
public class HeaderView {
    private HBox root;
    
    public HeaderView() {
        Label titulo = new Label("Concesionario de Vehículos");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2c3e50"));
        titulo.setPadding(new Insets(10));
        
        root = new HBox(titulo);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7; -fx-border-width: 0 0 2 0;");
    }

    public HBox getRoot() {
        return root;
    }
}
