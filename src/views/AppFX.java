/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        //MainView mainView = new MainView();
        //MainViewDos mainView = new MainViewDos();
        Main mainView = new Main();
                

        primaryStage.setTitle("Concesionario de Vehículos");
        primaryStage.setScene(new Scene(mainView.getRoot(), 985, 700));
        primaryStage.show();
    }

    /* 
     * @brief Método principal para lanzar la aplicación JavaFX.
    */
    public static void main(String[] args) {
        launch(args);
    }
}