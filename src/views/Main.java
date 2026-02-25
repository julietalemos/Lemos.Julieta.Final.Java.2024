/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import views.components.*;
import views.dialogs.*;
import controllers.VehiculoController;
import java.util.List;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import models.Vehiculo;

/**
 * Vista principal - coordina los componentes
 * Responsabilidad: ensamblar la interfaz, delegar acciones
 * @author julie
 */
public class Main {
    private VehiculoController controller;
    
    // Componentes
    private HeaderView header;
    private VehiculoTableView tableView;
    private PanelBotones panelBotones;
    
    private BorderPane root;
    
    public Main() {
        this.controller = new VehiculoController();
        inicializarComponentes();
        configurarLayout();
        conectarEventos();
    }
    
    private void inicializarComponentes() {
        this.header = new HeaderView();
        this.tableView = new VehiculoTableView();
        this.panelBotones = new PanelBotones();
    }

    private void configurarLayout() {
        root = new BorderPane();
        root.setTop(header.getRoot());
        root.setCenter(tableView.getRoot());
        root.setRight(panelBotones.getRoot());
        
        BorderPane.setMargin(tableView.getRoot(), new Insets(10));
    }

    private void conectarEventos() {
        // CRUD
        panelBotones.setOnAgregar(this::agregar);
        panelBotones.setOnActualizar(this::actualizar);
        panelBotones.setOnEliminar(this::eliminar);
        
        // Ordenar/Filtrar
        panelBotones.setOnOrdenar(this::ordenar);
        panelBotones.setOnFiltrarPorMarca(this::filtrarPorMarca);
        panelBotones.setOnFiltrarPorEstado(this::filtrarPorEstado);
        panelBotones.setOnMostrarTodos(this::actualizarTabla);
        
        // Interfaces funcionales
        panelBotones.setOnDescuentoGeneral(this::aplicarDescuentoGeneral);
        panelBotones.setOnDescuentoAntiguos(this::aplicarDescuentoAntiguos);
        
        // Persistencia
        panelBotones.setOnGuardar(this::mostrarDialogoGuardar);
        panelBotones.setOnCargar(this::mostrarDialogoCargar);
        panelBotones.setOnExportar(this::exportarTXT);
    }
    
    // ========== MÉTODOS DE ACCIÓN ==========

    private void agregar() {
        VehiculoFormDialog.mostrar().ifPresent(vehiculo -> {
            try {
                controller.crear(vehiculo);
                actualizarTabla();
                SimpleDialogs.mostrarExito("Vehículo agregado correctamente");
            } catch (Exception e) {
                SimpleDialogs.mostrarError("Error al agregar: " + e.getMessage());
            }
        });
    }

    private void actualizar() {
        tableView.getVehiculoSeleccionado().ifPresentOrElse(
            vehiculo -> {
                VehiculoFormDialog.mostrarEditar(vehiculo).ifPresent(actualizado -> {                   
                    try {
                        controller.actualizar(vehiculo.getId(), actualizado);
                        actualizarTabla();
                        SimpleDialogs.mostrarExito("Vehículo actualizado");
                    } catch (Exception e) {
                        SimpleDialogs.mostrarError("Error: " + e.getMessage());
                    }
                });
            },
            () -> SimpleDialogs.mostrarAdvertencia("Seleccione un vehículo de la tabla")
        );
    }

    private void eliminar() {
        tableView.getVehiculoSeleccionado().ifPresentOrElse(
            vehiculo -> {
                if (SimpleDialogs.confirmar("¿Eliminar " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "?")) {
                    try {
                        controller.eliminar(vehiculo.getId());
                        actualizarTabla();
                        SimpleDialogs.mostrarExito("Vehículo eliminado");
                    } catch (Exception e) {
                        SimpleDialogs.mostrarError("Error: " + e.getMessage());
                    }
                }
            },
            () -> SimpleDialogs.mostrarAdvertencia("Seleccione un vehículo")
        );
    }

    private void ordenar() {
        SimpleDialogs.seleccionarOrdenamiento().ifPresent(criterio -> {
            controller.ordenar(criterio);
            actualizarTabla();
        });
    }

    private void filtrarPorMarca() {
        FiltroDialog.filtrarPorMarca().ifPresent(marca -> {
            try {
                tableView.actualizarVehiculos((List<Vehiculo>) controller.filtrarPorMarca(marca));
            } catch (Exception e) {
                SimpleDialogs.mostrarError("No se encontraron vehículos de la marca: " + marca);
            }
        });
    }
    
    private void filtrarPorEstado() {
        FiltroDialog.filtrarPorEstado().ifPresent(estado -> {
            try {
                tableView.actualizarVehiculos((List<Vehiculo>) controller.filtrarPorEstado(estado));
            } catch (Exception e) {
                SimpleDialogs.mostrarError("No se encontraron vehículos en estado: " + estado);
            }
        });
    }

    private void aplicarDescuentoGeneral() {
        SimpleDialogs.ingresarPorcentaje("Descuento general").ifPresent(porcentaje -> {
            try {
                controller.aplicarDescuentoGeneral(porcentaje);
                actualizarTabla();
                SimpleDialogs.mostrarExito("Descuento del " + porcentaje + "% aplicado");
            } catch (Exception e) {
                SimpleDialogs.mostrarError("Error: " + e.getMessage());
            }
        });
    }

    private void aplicarDescuentoAntiguos() {
        FiltroDialog.mostrarDescuentoAntiguos().ifPresent(params -> {
            try {
                controller.aplicarDescuentoAntiguos(params.anios(), params.descuento());
                actualizarTabla();
                SimpleDialogs.mostrarExito("Descuento aplicado a vehículos antiguos");
            } catch (Exception e) {
                SimpleDialogs.mostrarError("Error: " + e.getMessage());
            }
        });
    }

    private void mostrarDialogoGuardar() {
        PersistenciaDialog.mostrarGuardar().ifPresent(resultado -> {
            try {
                controller.guardar(resultado.formato(), resultado.ruta());
                SimpleDialogs.mostrarExito("Datos guardados en " + resultado.formato());
            } catch (Exception e) {
                SimpleDialogs.mostrarError("Error al guardar: " + e.getMessage());
            }
        });
    }

    private void mostrarDialogoCargar() {
        PersistenciaDialog.mostrarCargar().ifPresent(ruta -> {
            try {
                controller.cargar(ruta);
                actualizarTabla();
                SimpleDialogs.mostrarExito("Datos cargados correctamente");
            } catch (Exception e) {
                SimpleDialogs.mostrarError("Error al cargar: " + e.getMessage());
            }
        });
    }

    private void exportarTXT() {
        PersistenciaDialog.seleccionarArchivoTXT().ifPresent(ruta -> {
            try {
                controller.exportarTXT(ruta);
                SimpleDialogs.mostrarExito("Listado exportado a TXT");
            } catch (Exception e) {
                SimpleDialogs.mostrarError("Error: " + e.getMessage());
            }
        });
    }

    private void actualizarTabla() {
        tableView.actualizarVehiculos(controller.listarTodos());
    }

    public BorderPane getRoot() {
        return root;
    }

    
}
