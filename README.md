# Concesionaria de Vehiculos - SobreRuedas

## Sobre mi
Mi nombre es Julieta Lemos, estudiante de la Tecnicatura en Programación de la UTN. Este es mi examen final de Programación II, con lenguaje Java.
## Resumen
La aplicación permite gestionar distintos tipos de vehículos (**autos, motos y camiones**) con funcionalidades como:
- CRUD completo: Crear, leer, actualizar y eliminar vehículos.
- Filtrado y ordenamiento por atributos (año, precio, tipo).
- Persistencia múltiple, distintos formatos: `.dat`, `.csv`, `.json`, `.txt`.
- Interfaz gráfica en JavaFX para visualizar y manipular la información.

### Capturas de pantalla
![Pantalla principal](CapturasProyecto/VistaGeneral.png)
![Agregar vehiculo](CapturasProyecto/AgregarVehiculo.png)
![Actualizar vehiculo](CapturasProyecto/EditarVehiculo.png)
![Eliminar Vehiculo](CapturasProyecto/EliminarVehiculo.png)
![Ordenamiento](CapturasProyecto/Ordenamiento.png)
![Filtro por marca](CapturasProyecto/FiltroPorMarca.png)
![Filtro por estado](CapturasProyecto/FiltroPorEstado.png)
![Descuento general](CapturasProyecto/DescuentoGeneral.png)
![Descuento Antiguedad](CapturasProyecto/DescuentoAntiguos.png)
![Dialogs Respuestas](CapturasProyecto/DialogsRespuestas.png)
![Persistencia](CapturasProyecto/GuardadoDatos.png)

### Diagrama UML
![Diagrama UML](ConcesionariaDiagramUML.png)

### Como usar la aplicación
Operaciones:
1. Agregar vehículo: Clic en "Agregar" → Completar formulario → "Aceptar"
2. Editar vehículo: Seleccionar vehículo → Clic en "Actualizar" → Editar datos → "Actualizar"
3. Eliminar: Seleccionar vehículo → Clic en "Eliminar" → Confirmar
4. Ordenar: Usar los ComboBox para elegir el tipo de ordenamiento → "Aceptar"
5. Filtrar: Clic en Marca o Estado → Ingresar criterio de filtro → "Aceptar"
6. Guardar: Seleccionar método de guardado (JSON, CSV, DAT) → Nombrar al archivo
7. Cargar: Seleccionar método de carga de archivo → Seleccionar archivo
8. Exportar: Exporta un archivo txt tipo Reporte con todos los vehiculos
