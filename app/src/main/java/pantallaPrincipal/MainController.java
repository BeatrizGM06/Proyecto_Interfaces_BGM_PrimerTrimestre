package pantallaPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class MainController implements Initializable {
    //Base de datos -> http://localhost:3306

    Connection conexion;
    Statement st;
    ResultSet rs;

    ObservableList<Objeto> listaObjeto = FXCollections.observableArrayList();
    ObservableList<Localizacion> listaLocalizacion = FXCollections.observableArrayList();
    ObservableList<Habitacion> listaHabitacion = FXCollections.observableArrayList();

    @FXML
    StackPane selectorPantalla;

    @FXML
    Pane pantallaObjeto;

    @FXML
    Pane pantallaHabitacion;

    @FXML
    Pane pantallaLocalizacion;

    @FXML
    Menu menuEditar;

    @FXML
    Menu menuVer;

    @FXML
    MenuItem menuItemBorrar;

    @FXML
    MenuItem menuItemEditar;

    @FXML
    MenuItem menuItemVer;

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

    //FUNCION DE AÑADIR
    @FXML
    void addButton(MouseEvent event) {
        /*
        String query = "UPDATE libros SET Titulo=?, Autor=?, Anyo=?, Paginas=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
            preparedStatement.setString(1, tituloField.getText());
            preparedStatement.setString(2, autorField.getText());
            preparedStatement.setInt(3, Integer.parseInt(anyoField.getText()));
            preparedStatement.setInt(4, Integer.parseInt(paginasField.getText()));
            preparedStatement.setInt(5, Integer.parseInt(idField.getText()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        mostrarLibros();
         */
    }

    @FXML
    private TableColumn<?, ?> columnaAbiertaHabitacion;

    @FXML
    private TableColumn<?, ?> columnaAltoObjeto;

    @FXML
    private TableColumn<?, ?> columnaAnchoObjeto;

    @FXML
    private TableColumn<?, ?> columnaDescripcionObjeto;

    @FXML
    private TableColumn<?, ?> columnaEditarHabitacion;

    @FXML
    private TableColumn<?, ?> columnaEditarLocalizacion;

    @FXML
    private TableColumn<?, ?> columnaEditarObjeto;

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
    private TableColumn<?, ?> columnaVerHabitacion;

    @FXML
    private TableColumn<?, ?> columnaVerLocalizacion;

    @FXML
    private TableColumn<?, ?> columnaVerObjeto;

    //FUNION DE BORRAR
    @FXML
    void deleteButton(MouseEvent event) {
        
    }

    //MOSTRAR LA PANTALLA DE EDITAR
    @FXML
    void editButton(ActionEvent event) {
        System.out.println(selectorPantalla.getChildren());
        if(pantallaHabitacion.isVisible()){
            //ABRIR verHabitacion.java
        }else if(pantallaLocalizacion.isVisible()){
            //ABRIR verLocalizacion.java
        }else{
            //ABRIR verObjeto.java
        }
    }

    @FXML
    void preguntasFrecuentes(ActionEvent event) {
        //ABRIR PÁGINA DE PREGUNTAS FRECUENTES
    }

    @FXML
    void reportarIncidente(ActionEvent event) {
        //ABRIR PANTALLA DE REPORTAR ACCIDENTE
    }

    @FXML
    void viewButton(ActionEvent event) {

    }

    //MOSTRAR LA PANTALLA DE ACCESIBILIDAD
    @FXML
    void mostrarAccesibilidad(ActionEvent event) {
        
    }

    @FXML
    void mostrarVentanaHabitacion(ActionEvent event) {
        pantallaHabitacion.setVisible(true);
        pantallaHabitacion.toFront();
        pantallaLocalizacion.setVisible(false);
        pantallaObjeto.setVisible(false);
    }

    @FXML
    void mostrarVentanaLocalizacion(ActionEvent event) {
        pantallaLocalizacion.setVisible(true);
        pantallaLocalizacion.toFront();
        pantallaHabitacion.setVisible(false);
        pantallaObjeto.setVisible(false);
    }

    @FXML
    void mostrarVentanaObjetos(ActionEvent event) {
        pantallaObjeto.setVisible(true);
        pantallaObjeto.toFront();
        pantallaLocalizacion.setVisible(false);
        pantallaHabitacion.setVisible(false);
    }

    public void cargarObjetos() {
        tablaObjeto.setItems(dameListaObjetos());
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
                    /*
                     * (rs.getInt("id_objeto"),
                                        rs.getString("TITULO"),
                                        rs.getString("Autor"),
                                        rs.getInt("Anyo"), rs.getInt("Paginas"));
                     */
                    listaObjeto.add(objeto);
                }
            } catch (SQLException e) {
                System.out.println("Excepción SQL: " + e.getMessage());
            }
            return listaObjeto;
        }
        return null;
        
    }

    public void cargarLocalizaciones() {
        tablaLocalizacion.setItems(dameListaLocalizacion());
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
                    /*
                     * (rs.getInt("id_objeto"),
                                        rs.getString("TITULO"),
                                        rs.getString("Autor"),
                                        rs.getInt("Anyo"), rs.getInt("Paginas"));
                     */
                    listaLocalizacion.add(localizacion);
                }
            } catch (SQLException e) {
                System.out.println("Excepción SQL: " + e.getMessage());
            }
            return listaLocalizacion;
        }
        return null;
        
    }

    public void cargarHabitaciones() {
        tablaHabitacion.setItems(dameListaHabitacion());
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
                    /*
                     * (rs.getInt("id_objeto"),
                                        rs.getString("TITULO"),
                                        rs.getString("Autor"),
                                        rs.getInt("Anyo"), rs.getInt("Paginas"));
                     */
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
        // cambiar, no tendría
        // que cambiar nada internamente, o al menos, el mínimo posible.
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
            //columnaVerObjeto.setCellValueFactory(new PropertyValueFactory<>("Ver"));
            //columnaEditarObjeto.setCellValueFactory(new PropertyValueFactory<>("Editar"));
            columnaIdObjeto.setCellValueFactory(new PropertyValueFactory<>("id_objeto"));
            columnaNombreObjeto.setCellValueFactory(new PropertyValueFactory<>("nombre_objeto"));
            columnaAltoObjeto.setCellValueFactory(new PropertyValueFactory<>("alto"));
            columnaAnchoObjeto.setCellValueFactory(new PropertyValueFactory<>("ancho"));
            columnaDescripcionObjeto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tablaObjeto.setItems(listaObjetos);

            ObservableList<Localizacion> listaLocalizacion = dameListaLocalizacion();
            // Los campos han de coincidir con los campos del objeto Localizacion
            //columnaVerLocalizacion.setCellValueFactory(new PropertyValueFactory<>("Ver"));
            //columnaEditarLocalizacion.setCellValueFactory(new PropertyValueFactory<>("Editar"));
            columnaObjetoLocalizacion.setCellValueFactory(new PropertyValueFactory<>("Objeto"));
            columnaHabitacionLocalizacion.setCellValueFactory(new PropertyValueFactory<>("Habitacion"));
            columnaPosicionX.setCellValueFactory(new PropertyValueFactory<>("Posicion_x"));
            columnaPosicionY.setCellValueFactory(new PropertyValueFactory<>("Posicion_y"));
            columnaInteractuable.setCellValueFactory(new PropertyValueFactory<>("Interactuable"));

            tablaLocalizacion.setItems(listaLocalizacion);

            ObservableList<Habitacion> listaHabitacion = dameListaHabitacion();
            // Los campos han de coincidir con los campos del objeto Habitacion
            //columnaVerHabitacion.setCellValueFactory(new PropertyValueFactory<>("Ver"));
            //columnaEditarHabitacion.setCellValueFactory(new PropertyValueFactory<>("Editar"));
            columnaIdHabitacion.setCellValueFactory(new PropertyValueFactory<>("Id_Habitacion"));
            columnaNumeroHabitacion.setCellValueFactory(new PropertyValueFactory<>("Num_Habitacion"));
            columnaPasilloHabitacion.setCellValueFactory(new PropertyValueFactory<>("NumPasillo"));
            columnaEmocionHabitacion.setCellValueFactory(new PropertyValueFactory<>("Fk_NombreEmocion"));
            columnaAbiertaHabitacion.setCellValueFactory(new PropertyValueFactory<>("Abierta"));

            tablaHabitacion.setItems(listaHabitacion);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conexion = this.getConnection();
            if (conexion != null) {
                this.st = conexion.createStatement();
            }
        } catch (IOException | SQLException e) {
        }
        
        setColumnas();

        pantallaHabitacion.setVisible(false);
        pantallaLocalizacion.setVisible(false);

        //Cargar los datos en la tabla
        cargarObjetos();

        

        Platform.runLater(() -> {
            // PROBLEMA: Qué ocurre si necesitamos acceder al Stage o Scene para hacer algo,
            // Por ejemplo: cerrar ventana y hacer algo con alguna variable del
            // Controlador??
            // En Main NO se puede hacer esto, porque aún no se ha cargado nada.
            // Además, en Initialize no se puede hacer porque tampoco se ha cargado nada.
            // SOLUCIÓN: Acceso dentro de un hilo
            // Accedemos al stage actual mediante cualquier nodo
            Stage primaryStage = (Stage) this.tablaObjeto.getScene().getWindow();
            primaryStage.setOnCloseRequest(event -> {
                try {
                    if(this.conexion!=null){
                        this.conexion.close();
                    }
                    System.out.println("Conex. a BBDD cerrada");
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorLibros.class.getName()).log(Level.SEVERE, null, ex);
                }
                primaryStage.close(); // Cierra la ventana si el usuario confirma
            });

        });
    }
}
