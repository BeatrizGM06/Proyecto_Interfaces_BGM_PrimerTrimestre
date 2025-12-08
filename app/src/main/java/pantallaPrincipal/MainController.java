package pantallaPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pantallaCrearOEditar.crearEditarHabitacion;
import pantallaCrearOEditar.crearEditarLocalizacion;
import pantallaCrearOEditar.crearEditarObjeto;
import pantallaVer.vistaHabitacionController;
import pantallaVer.vistaLocalizacionController;
import pantallaVer.vistaObjetoController;

import static java.lang.System.exit;

public class MainController implements Initializable {
    // Base de datos -> http://localhost:3306

    // DECLARACIÓN DE VARIABLES ----------------------------------
    Connection conexion;
    Statement st;
    ResultSet rs;

    ObservableList<Objeto> listaObjeto = FXCollections.observableArrayList();
    ObservableList<Localizacion> listaLocalizacion = FXCollections.observableArrayList();
    ObservableList<Habitacion> listaHabitacion = FXCollections.observableArrayList();

    private vistaHabitacionController controladorHabitacion;
    private vistaLocalizacionController controladorLocalizacion;
    private vistaObjetoController controladorObjeto;
    private crearEditarHabitacion controladorHabitacionEdit;
    private crearEditarLocalizacion controladorLocalizacionEdit;
    private crearEditarObjeto controladorObjetoEdit;
    

    @FXML
    StackPane selectorPantalla;

    @FXML
    Pane pantallaObjeto;

    @FXML
    Pane pantallaHabitacion;

    @FXML
    Pane pantallaLocalizacion;

    @FXML
    MenuItem menuItemBorrar;

    @FXML
    TableView<Objeto> tablaObjeto;

    @FXML
    TableView<Localizacion> tablaLocalizacion;

    @FXML
    TableView<Habitacion> tablaHabitacion;

    @FXML
    Button botonBorrar;

    @FXML
    Button botonAdd;

    @FXML
    private TableColumn<?, ?> columnaAbiertaHabitacion;

    @FXML
    private TableColumn<?, ?> columnaAltoObjeto;

    @FXML
    private TableColumn<?, ?> columnaAnchoObjeto;

    @FXML
    private TableColumn<?, ?> columnaDescripcionObjeto;

    @FXML
    private TableColumn<?, ?> columnaEmocionHabitacion;

    @FXML
    private TableColumn<?, ?> columnaHabitacionLocalizacion;

    @FXML
    private TableColumn<?, ?> columnaIdHabitacion;

    @FXML
    private TableColumn<?, ?> columnaIdObjeto;

    @FXML
    private TableColumn<?, ?> columnaInteractuable;

    @FXML
    private TableColumn<?, ?> columnaNombreObjeto;

    @FXML
    private TableColumn<?, ?> columnaNumeroHabitacion;

    @FXML
    private TableColumn<?, ?> columnaObjetoLocalizacion;

    @FXML
    private TableColumn<?, ?> columnaPasilloHabitacion;

    @FXML
    private TableColumn<?, ?> columnaPosicionX;

    @FXML
    private TableColumn<?, ?> columnaPosicionY;

    @FXML
    private TableColumn<Habitacion, HBox> columnaAccionHabitacion;

    @FXML
    private TableColumn<Localizacion, HBox> columnaAccionLocalizacion;

    @FXML
    private TableColumn<Objeto, HBox> columnaAccionObjeto;

    //Definimos una instancia del otro Stage (solo uno porque no habrá más de 2 ventanas abiertas simultaneamente)
    Stage stageB;

    //FUNCION DE BORRAR ------------------------------------------
    @FXML
    void deleteButton(ActionEvent event) {        
        TableView tablaActual = getTablaActiva();

        if(tablaActual.equals(tablaHabitacion)){
            borrarHabitacion();
        }else if(tablaActual.equals(tablaLocalizacion)){
            borrarLocalizacion();
        }else if(tablaActual.equals(tablaObjeto)){
            borrarObjeto();
        }else{
            System.out.println("No se encuentra la tabla actual");
        }
        

        cargarDatosTabla();
    }

    private void borrarHabitacion(){
        Habitacion habitacionSeleccionada = tablaHabitacion.getSelectionModel().getSelectedItem();

        int id = habitacionSeleccionada.getId_Habitacion();
        

        //COMPROBACION DE QUE FUNCIONA
        if(habitacionSeleccionada == null){
            System.out.println("NO SE HA SELECCIONADO HABITACIÓN");
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ELIMINAR");
                alert.setHeaderText("Confirmación de borrado");
                alert.setContentText("¿Deseas borrar "+habitacionSeleccionada.getId_Habitacion()+"?");
                ButtonType resultado=alert.showAndWait().orElse(ButtonType.CANCEL);
            if(resultado == javafx.scene.control.ButtonType.OK){
                String query = "DELETE FROM Habitacion WHERE id_habitacion=?";
                try {
                    PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Excepción: " + e.getMessage());
                }
            }else{
                System.out.println("Cancelando borrado...");
            }
        }
    }

    private void borrarLocalizacion(){
        //TODO
        Localizacion localizacionSeleccionada = tablaLocalizacion.getSelectionModel().getSelectedItem();

        int idHabitacion = localizacionSeleccionada.getHabitacion();
        int idObjeto = localizacionSeleccionada.getObjeto();
        

        //COMPROBACION DE QUE FUNCIONA
        if(localizacionSeleccionada.equals(null)){
            System.out.println("NO SE HA SELECCIONADO LOCALIZACIÓN");
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ELIMINAR");
                alert.setHeaderText("Confirmación de borrado");
                alert.setContentText("¿Deseas borrar la localización?");
                ButtonType resultado=alert.showAndWait().orElse(ButtonType.CANCEL);
            if(resultado == javafx.scene.control.ButtonType.OK){
                String query = "DELETE FROM Localizacion WHERE fk_objeto=? AND fk_habitacion=?";
                try {
                    PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                    preparedStatement.setInt(1, idObjeto);
                    preparedStatement.setInt(2, idHabitacion);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Excepción: " + e.getMessage());
                }
            }else{
                System.out.println("Cancelando borrado...");
            }
        }
    }

    private void borrarObjeto(){
        Objeto objetoSeleccionado = tablaObjeto.getSelectionModel().getSelectedItem();

        int id = objetoSeleccionado.getId_objeto();
        

        //COMPROBACION DE QUE FUNCIONA
        if(objetoSeleccionado.equals(null)){
            System.out.println("NO SE HA SELECCIONADO OBJETO");
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ELIMINAR");
                alert.setHeaderText("Confirmación de borrado");
                alert.setContentText("¿Deseas borrar "+objetoSeleccionado.getNombre_objeto()+"?");
                ButtonType resultado=alert.showAndWait().orElse(ButtonType.CANCEL);
            if(resultado == javafx.scene.control.ButtonType.OK){
                String query = "DELETE FROM Objeto WHERE id_objeto=?";
                try {
                    PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Excepción: " + e.getMessage());
                }
            }else{
                System.out.println("Cancelando borrado...");
            }
        }
    }

    //FUNCION DE AÑADIR ------------------------------------------
    @FXML
    void addButton(ActionEvent event) {
        TableView tablaActiva = getTablaActiva();

        if(tablaActiva.equals(tablaHabitacion)){
            Habitacion h = null;
            abrirEditarHabitacion(h, false);
        }else if(tablaActiva.equals(tablaLocalizacion)){
            Localizacion l = null;
            abrirEditarLocalizacion(l, false);
        }else if(tablaActiva.equals(tablaObjeto)){
            Objeto o = null;
            abrirEditarObjeto(o, false);
        }else{
            System.out.println("Error: No se encuentra la tabla activa");
        }

        cargarDatosTabla();
    }

    private void desactivarSelActual(){
        tablaHabitacion.getSelectionModel().clearSelection();
        tablaLocalizacion.getSelectionModel().clearSelection();
        tablaObjeto.getSelectionModel().clearSelection();
    }

    @FXML
    void mostrarVentanaHabitacion(ActionEvent event) {
        desactivarSelActual();

        pantallaHabitacion.setVisible(true);
        pantallaHabitacion.toFront();
        pantallaLocalizacion.setVisible(false);
        pantallaObjeto.setVisible(false);

        comprobarSeleccionado();
    }

    @FXML
    void mostrarVentanaLocalizacion(ActionEvent event) {
        desactivarSelActual();

        pantallaLocalizacion.setVisible(true);
        pantallaLocalizacion.toFront();
        pantallaHabitacion.setVisible(false);
        pantallaObjeto.setVisible(false);

        comprobarSeleccionado();
    }

    @FXML
    void mostrarVentanaObjetos(ActionEvent event) {
        desactivarSelActual();

        pantallaObjeto.setVisible(true);
        pantallaObjeto.toFront();
        pantallaLocalizacion.setVisible(false);
        pantallaHabitacion.setVisible(false);

        comprobarSeleccionado();
    }

    public ObservableList<Objeto> dameListaObjetos() {
        if (conexion != null) {
            listaObjeto.clear(); // Limpiamos el contenido actual
            String query = "SELECT * FROM Objeto";
            try {
                rs = st.executeQuery(query);
                Objeto objeto;
                while (rs.next()) { // Se usan los identificadores propios en la BBDD (no es case sensitive).
                                    // Revisar en phpmyadmin
                    objeto = new Objeto(rs.getInt("id_objeto"),
                                        rs.getString("nombre_objeto"),
                                        rs.getString("archivo_objeto"),
                                        rs.getString("descripcion"),
                                        rs.getInt("alto"),
                                        rs.getInt("ancho"));
                    listaObjeto.add(objeto);
                }
            } catch (SQLException e) {
                System.out.println("Excepción SQL: " + e.getMessage());
            }
            return listaObjeto;
        }
        return null;
        
    }

    public ObservableList<Localizacion> dameListaLocalizacion() {
        if (conexion != null) {
            listaLocalizacion.clear(); // Limpiamos el contenido actual
            String query = "SELECT * FROM Localizacion";
            try {
                rs = st.executeQuery(query);
                Localizacion localizacion;
                while (rs.next()) { // Se usan los identificadores propios en la BBDD (no es case sensitive).
                                    // Revisar en phpmyadmin
                    localizacion = new Localizacion(rs.getInt("fk_objeto"),
                                        rs.getInt("fk_habitacion"),
                                        rs.getInt("posicion_x"),
                                        rs.getInt("posicion_y"),
                                        rs.getBoolean("interactuable"));
                    listaLocalizacion.add(localizacion);
                }
            } catch (SQLException e) {
                System.out.println("Excepción SQL: " + e.getMessage());
            }
            return listaLocalizacion;
        }
        return null;
        
    }

    public ObservableList<Habitacion> dameListaHabitacion() {
        if (conexion != null) {
            listaHabitacion.clear(); // Limpiamos el contenido actual
            String query = "SELECT * FROM Habitacion";
            try {
                rs = st.executeQuery(query);
                Habitacion habitacion;
                while (rs.next()) { // Se usan los identificadores propios en la BBDD (no es case sensitive).
                                    // Revisar en phpmyadmin
                    habitacion = new Habitacion(rs.getInt("id_habitacion"),
                                        rs.getInt("num_habitacion"),
                                        rs.getInt("fk_numPasillo"),
                                        rs.getString("fk_nombreEmocion"),
                                        rs.getBoolean("abierta"));
                    listaHabitacion.add(habitacion);
                }
            } catch (SQLException e) {
                System.out.println("Excepción SQL: " + e.getMessage());
            }
            return listaHabitacion;
        }
        return null;
        
    }

    public Connection getConnection() throws IOException {
        // Importante: hay que separar los datos de conexión del programa, así, al
        // cambiar, no tendría que cambiar nada internamente, o al menos, el mínimo posible.
        Properties properties = new Properties();
        String IP, PORT, BBDD, USER, PWD;

        // Se lee IP desde fuera del jar
        try {
            InputStream input_ip = new FileInputStream("ip.properties");// archivo debe estar junto al jar
            properties.load(input_ip);
            IP = (String) properties.get("IP");
        } catch (FileNotFoundException e) {
            System.out.println(
                    "No se pudo encontrar el archivo de propiedades para IP, se establece localhost por defecto");
            IP = "localhost";
        }

        InputStream input = getClass().getClassLoader().getResourceAsStream("bbdd.properties");
        if (input == null) {
            System.out.println("No se pudo encontrar el archivo de propiedades");
            return null;
        } else {
            // Cargar las propiedades desde el archivo
            properties.load(input);
            // String IP = (String) properties.get("IP"); //Tiene sentido leerlo desde fuera
            // del Jar por si cambiamos la IP, el resto no debería de cambiar
            // ni debería ser público
            PORT = (String) properties.get("PORT");// En vez de crear con new, lo crea por asignación + casting
            BBDD = (String) properties.get("BBDD");
            USER = (String) properties.get("USER");// USER en LAMP
            PWD = (String) properties.get("PWD");// PWD en LAMP

            Connection conn;
            try {
                String cadconex = "jdbc:mysql://" + IP + ":" + PORT + "/" + BBDD + " USER:" + USER + "PWD:" + PWD;
                System.out.println(cadconex);
                // Si usamos LAMP Funciona con ambos conectores
                conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + BBDD, USER, PWD);
                return conn;
            } catch (SQLException e) {
                System.out.println("Error SQL: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error de conexión");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                exit(0);
                return null;
            }
        }
    }

    private void setColumnas(){

        if (conexion != null) {
            ObservableList<Objeto> listaObjetos = dameListaObjetos();
            // Los campos han de coincidir con los campos del objeto Objeto
            columnaAccionObjeto.setCellValueFactory(cellData -> {
                Objeto o = cellData.getValue();

                ImageView imagenVer=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("ver.png")));
                ImageView imagenEditar=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("editar.png")));
                imagenEditar.setFitHeight(15);
                imagenEditar.setFitWidth(15);
                imagenVer.setFitHeight(15);
                imagenVer.setFitWidth(15);

                Button botonVer = new Button("", imagenVer); //imagenVer
                botonVer.setOnAction(event -> {
                    System.out.println("Mostrando objeto " + o.getId_objeto());
                    abrirVerObjeto(o);
                });

                Button botonEditar = new Button("", imagenEditar); //imagenEditar
                botonEditar.setOnAction(event -> {
                    System.out.println("Editando objeto " + o.getId_objeto());
                    abrirEditarObjeto(o, true);
                });

                HBox accion = new HBox(botonVer,botonEditar);
                return new SimpleObjectProperty<>(accion);
            });
            

            columnaIdObjeto.setCellValueFactory(new PropertyValueFactory<>("id_objeto"));
            columnaNombreObjeto.setCellValueFactory(new PropertyValueFactory<>("nombre_objeto"));
            columnaAltoObjeto.setCellValueFactory(new PropertyValueFactory<>("alto"));
            columnaAnchoObjeto.setCellValueFactory(new PropertyValueFactory<>("ancho"));
            columnaDescripcionObjeto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tablaObjeto.setItems(listaObjetos);

            ObservableList<Localizacion> listaLocalizacion = dameListaLocalizacion();
            // Los campos han de coincidir con los campos del objeto Localizacion
            columnaAccionLocalizacion.setCellValueFactory(cellData -> {
                ImageView imagenVer=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("ver.png")));
                ImageView imagenEditar=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("editar.png")));
                imagenEditar.setFitHeight(15);
                imagenEditar.setFitWidth(15);
                imagenVer.setFitHeight(15);
                imagenVer.setFitWidth(15);

                Localizacion l = cellData.getValue();

                Button botonVer = new Button("", imagenVer); //imagenVer
                botonVer.setOnAction(event -> {
                    System.out.println("Mostrando localización de objeto " + l.getObjeto()+" en habitación "+l.getHabitacion());
                    abrirVerLocalizacion(l);
                });

                Button botonEditar = new Button("", imagenEditar); //imagenEditar
                botonEditar.setOnAction(event -> {
                    System.out.println("Editando localización de objeto " + l.getObjeto()+" en habitación "+l.getHabitacion());
                    abrirEditarLocalizacion(l, true);
                });

                HBox accion = new HBox(botonVer,botonEditar);
                return new SimpleObjectProperty<>(accion);
            });


            columnaObjetoLocalizacion.setCellValueFactory(new PropertyValueFactory<>("Objeto"));
            columnaHabitacionLocalizacion.setCellValueFactory(new PropertyValueFactory<>("Habitacion"));
            columnaPosicionX.setCellValueFactory(new PropertyValueFactory<>("Posicion_x"));
            columnaPosicionY.setCellValueFactory(new PropertyValueFactory<>("Posicion_y"));
            columnaInteractuable.setCellValueFactory(new PropertyValueFactory<>("Interactuable"));

            tablaLocalizacion.setItems(listaLocalizacion);

            ObservableList<Habitacion> listaHabitacion = dameListaHabitacion();
            // Los campos han de coincidir con los campos del objeto Habitacion
            columnaAccionHabitacion.setCellValueFactory(cellData -> {
                ImageView imagenVer=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("ver.png")));
                ImageView imagenEditar=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("editar.png")));
                imagenEditar.setFitHeight(15);
                imagenEditar.setFitWidth(15);
                imagenVer.setFitHeight(15);
                imagenVer.setFitWidth(15);

                Habitacion h = cellData.getValue();

                Button botonVer = new Button("", imagenVer); //imagenVer
                botonVer.setOnAction(event -> {
                    System.out.println("Mostrando habitación " + h.getId_Habitacion());
                    abrirVerHabitacion(h);
                });

                Button botonEditar = new Button("", imagenEditar); //imagenEditar
                botonEditar.setOnAction(event -> {
                    System.out.println("Editando habitación " + h.getId_Habitacion());
                    abrirEditarHabitacion(h, true);
                });

                HBox accion = new HBox(botonVer,botonEditar);
                return new SimpleObjectProperty<>(accion);
            });

            columnaIdHabitacion.setCellValueFactory(new PropertyValueFactory<>("Id_Habitacion"));
            columnaNumeroHabitacion.setCellValueFactory(new PropertyValueFactory<>("Num_Habitacion"));
            columnaPasilloHabitacion.setCellValueFactory(new PropertyValueFactory<>("NumPasillo"));
            columnaEmocionHabitacion.setCellValueFactory(new PropertyValueFactory<>("Fk_NombreEmocion"));
            columnaAbiertaHabitacion.setCellValueFactory(new PropertyValueFactory<>("Abierta"));

            tablaHabitacion.setItems(listaHabitacion);
        }
    }

    //------------------------------------------------------------

    public void cargarObjetos() {
        tablaObjeto.setItems(dameListaObjetos());
    }

    public void cargarLocalizaciones() {
        tablaLocalizacion.setItems(dameListaLocalizacion());
    }

    public void cargarHabitaciones() {
        tablaHabitacion.setItems(dameListaHabitacion());
    }


    private void abrirVerHabitacion(Habitacion habitacion){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ver/vistaHabitacion.fxml"));

        System.out.println("CARGAR NUEVA VENTANA");
        
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("Error cargando root: "+e.getMessage());
        }

        this.controladorHabitacion = (vistaHabitacionController) loader.getController();
        this.controladorHabitacion.setHabitacion(habitacion);
        
        Scene escena = new Scene(root);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL);
        stageB.setResizable(false);
        stageB.setScene(escena);
        stageB.setTitle("Ver Habitación");
        stageB.showAndWait();
    }

    private void abrirVerLocalizacion(Localizacion localizacion){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ver/vistaLocalizacion.fxml"));

        System.out.println("CARGAR NUEVA VENTANA");
        
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("Error cargando root: "+e.getMessage());
        }

        this.controladorLocalizacion = (vistaLocalizacionController) loader.getController();

        this.controladorLocalizacion.setLocalizacion(localizacion);

        Scene escena = new Scene(root);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL);
        stageB.setResizable(false);
        stageB.setScene(escena);
        stageB.setTitle("Ver Localización");
        stageB.showAndWait();
    }

    private void abrirVerObjeto(Objeto objeto){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ver/vistaObjeto.fxml"));

        System.out.println("CARGAR NUEVA VENTANA");
        
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("Error cargando root: "+e.getMessage());
        }

        this.controladorObjeto = (vistaObjetoController) loader.getController();

        this.controladorObjeto.setObjeto(objeto);
        
        Scene escena = new Scene(root);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL);
        stageB.setResizable(false);
        stageB.setScene(escena);
        stageB.setTitle("Ver Objeto");
        stageB.showAndWait();
    }

    private void abrirEditarHabitacion(Habitacion habitacion, boolean editar){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editar/vistaEditarHabitacion.fxml"));

        System.out.println("CARGAR NUEVA VENTANA");
        
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("Error cargando root: "+e.getMessage());
        }

        this.controladorHabitacionEdit = (crearEditarHabitacion) loader.getController();
        this.controladorHabitacionEdit.setEnlace(this);
        this.controladorHabitacionEdit.setEditar(editar);
        if(editar){
            this.controladorHabitacionEdit.setHabitacion(habitacion);
        }else{
            this.controladorHabitacionEdit.setHabitacion(null);
        }

        Scene escena = new Scene(root);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL);
        stageB.setResizable(false);
        stageB.setScene(escena);
        stageB.setTitle("Editar Habitación");
        stageB.showAndWait();
    }

    private void abrirEditarLocalizacion(Localizacion localizacion, boolean editar){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editar/vistaEditarLocalizacion.fxml"));

        System.out.println("CARGAR NUEVA VENTANA");
        
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("Error cargando root: "+e.getMessage());
        }

        this.controladorLocalizacionEdit = (crearEditarLocalizacion) loader.getController();
        this.controladorLocalizacionEdit.setEnlace(this);

        this.controladorLocalizacionEdit.setEditar(editar);
        if(editar){
            this.controladorLocalizacionEdit.setLocalizacion(localizacion);
        }else{
            this.controladorLocalizacionEdit.setLocalizacion(null);
        }

        Scene escena = new Scene(root);
        stageB = new Stage();
        //stageB.initModality(Modality.APPLICATION_MODAL); Para poder buscar en los demás apartados, a este en concreto no le pongo modal
        stageB.setResizable(false);
        stageB.setScene(escena);
        stageB.setTitle("Editar Localización");
        stageB.showAndWait();
    }

    private void abrirEditarObjeto(Objeto objeto, boolean editar){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/editar/vistaEditarObjeto.fxml"));

        System.out.println("CARGAR NUEVA VENTANA");
        
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("Error cargando root: "+e.getMessage());
        }

        this.controladorObjetoEdit = (crearEditarObjeto) loader.getController();
        this.controladorObjetoEdit.setEnlace(this);

        this.controladorObjetoEdit.setEditar(editar);
        if(editar){
            this.controladorObjetoEdit.setObjeto(objeto);
        }else{
            this.controladorObjetoEdit.setObjeto(null);
        }

        Scene escena = new Scene(root);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL);
        stageB.setResizable(false);
        stageB.setScene(escena);
        stageB.setTitle("Editar objeto");
        stageB.showAndWait();
    }


    private TableView getTablaActiva(){
        return pantallaHabitacion.isVisible()?tablaHabitacion:
                                pantallaLocalizacion.isVisible()?tablaLocalizacion:
                                pantallaObjeto.isVisible()?tablaObjeto:
                                null;
    }

    private void desactivarEdiciones(boolean desactivar){
        menuItemBorrar.setDisable(desactivar);
        botonBorrar.setDisable(desactivar);
    }

    //COMPRUEBA Y ACTIVA O DESACTIVA BOTONES ---------------------
    private void comprobarSeleccionado(){
        TableView tablaVisible = getTablaActiva();

        if(tablaVisible != null){
            tablaVisible.getSelectionModel().selectedItemProperty().addListener((observable, viejoValor, nuevoValor) -> {
                if(nuevoValor != null){
                    //Si hay un objeto seleccionado:
                    desactivarEdiciones(false);
                }else{
                    //Si no hay un objeto seleccionado:
                    desactivarEdiciones(true);
                }
            });
        }else{
            System.out.println("Error: no se carga la tabla");
        }
    }

    //CARGAR DATOS DE LA TABLA
    public void cargarDatosTabla(){
        cargarObjetos();
        cargarHabitaciones();
        cargarLocalizaciones();
    }

    //FUNCIONES QUE SE ACTIVAN DESDE LOS CONTROLADORES -----------
    public void recibirObjeto(Objeto objeto, boolean editar){
        if(editar){
            String query = "UPDATE Objeto SET nombre_objeto=?,archivo_objeto=?,descripcion=?,alto=?,ancho=? WHERE id_objeto=?";
            try {
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setString(1, objeto.getNombre_objeto());
                preparedStatement.setString(2, objeto.getArchivo_objeto());
                preparedStatement.setString(3, objeto.getDescripcion());
                preparedStatement.setInt(4, objeto.getAlto());
                preparedStatement.setInt(5, objeto.getAncho());
                preparedStatement.setInt(6, objeto.getId_objeto());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Excepción: " + e.getMessage());
            }

        }else{
            String query = "INSERT INTO Objeto(nombre_objeto,archivo_objeto,descripcion,alto,ancho) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setString(1, objeto.getNombre_objeto());
                preparedStatement.setString(2, objeto.getArchivo_objeto());
                preparedStatement.setString(3, objeto.getDescripcion());
                preparedStatement.setInt(4, objeto.getAlto());
                preparedStatement.setInt(5, objeto.getAncho());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }

        cargarDatosTabla();
    }

    public void recibirLocalizacion(Localizacion localizacion, Localizacion localizacionAnterior, boolean editar){
        if(editar){
            String query = "UPDATE Localizacion SET fk_objeto=?,fk_habitacion=?,posicion_x=?,posicion_y=?,interactuable=? WHERE fk_objeto=? AND fk_habitacion=?";
            try {
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setInt(1, localizacion.getObjeto());
                preparedStatement.setInt(2, localizacion.getHabitacion());
                preparedStatement.setInt(3, localizacion.getPosicion_x());
                preparedStatement.setInt(4, localizacion.getPosicion_y());
                preparedStatement.setBoolean(5, localizacion.getInteractuable());
                preparedStatement.setInt(6, localizacionAnterior.getObjeto());
                preparedStatement.setInt(7, localizacionAnterior.getHabitacion());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }else{
            String query = "INSERT INTO Localizacion(fk_objeto,fk_habitacion,posicion_x,posicion_y,interactuable) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setInt(1, localizacion.getObjeto());
                preparedStatement.setInt(2, localizacion.getHabitacion());
                preparedStatement.setInt(3, localizacion.getPosicion_x());
                preparedStatement.setInt(4, localizacion.getPosicion_y());
                preparedStatement.setBoolean(5, localizacion.getInteractuable());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }
        cargarDatosTabla();
    }

    public void recibirHabitacion(Habitacion habitacion, boolean editar){
        if(editar){
            String query = "UPDATE Habitacion SET num_habitacion=?,fk_numPasillo=?,fk_nombreEmocion=?,abierta=? WHERE id_habitacion=?";
            try {
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setInt(1, habitacion.getNum_Habitacion());
                preparedStatement.setInt(2, habitacion.getNumPasillo());
                preparedStatement.setString(3, habitacion.getFk_NombreEmocion());
                preparedStatement.setBoolean(4, habitacion.getAbierta());
                preparedStatement.setInt(5,habitacion.getId_Habitacion());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }else{
            String query = "INSERT INTO Habitacion(num_habitacion, fk_numPasillo, fk_nombreEmocion, abierta) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setInt(1, habitacion.getNum_Habitacion());
                preparedStatement.setInt(2, habitacion.getNumPasillo());
                preparedStatement.setString(3, habitacion.getFk_NombreEmocion());
                preparedStatement.setBoolean(4, habitacion.getAbierta());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }
        cargarDatosTabla();
    }

    //INITIALIZE -------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conexion = this.getConnection();
            if (conexion != null) {
                this.st = conexion.createStatement();
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        
        setColumnas();

        pantallaHabitacion.setVisible(false);
        pantallaLocalizacion.setVisible(false);

        cargarDatosTabla();

        comprobarSeleccionado();

        Platform.runLater(() -> {
            Stage primaryStage = (Stage) this.tablaObjeto.getScene().getWindow();
            primaryStage.setOnCloseRequest(event -> {
                try {
                    if(this.conexion!=null){
                        this.conexion.close();
                    }
                    System.out.println("Conex. a BBDD cerrada");
                } catch (SQLException e) {
                    System.out.println("Error: "+e.getMessage());
                }
                primaryStage.close(); // Cierra la ventana si el usuario confirma
            });
        });
    }
}
