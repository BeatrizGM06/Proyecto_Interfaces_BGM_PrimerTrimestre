package pantallaCrearOEditar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pantallaPrincipal.Habitacion;
import pantallaPrincipal.Localizacion;
import pantallaPrincipal.MainController;
import pantallaPrincipal.Objeto;

public class crearEditarLocalizacion implements Initializable {
    private boolean editar=true;
    private Localizacion localizacion=null;
    private MainController mainController;
    private Localizacion localizacionNueva;

    private ObservableList<String> listaObjetos = FXCollections.observableArrayList();
    private ObservableList<String> listaHabitaciones = FXCollections.observableArrayList();

     @FXML
    private CheckBox check_interactuable;

    @FXML
    private ComboBox<String> id_habitacion;

    @FXML
    private ComboBox<String> id_objeto;

    @FXML
    private TextField posX;

    @FXML
    private TextField posY;

    @FXML
    void Aceptar(ActionEvent event) {
        if(comprobarDatos()){
            crearNuevaLocalizacion();

            //ALERT EXITO
            Alert alertExito=new Alert(AlertType.INFORMATION);
            alertExito.setTitle("Éxito");
            if(this.editar){
                alertExito.setHeaderText("Edición completada");
                alertExito.setContentText("Se ha editado la localización");
            }else{
                alertExito.setHeaderText("Creación completada");
                alertExito.setContentText("Se ha creado una nueva localización");
            }
            alertExito.showAndWait();

            //CERRAR STAGE
            Stage primaryStage = (Stage) this.id_objeto.getScene().getWindow();
            primaryStage.close();
        }else{
            Alert alertError=new Alert(AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Datos no válidos");
            alertError.setContentText("Edite los datos en un formato válido...");
            alertError.showAndWait();
        }
    }

    private void crearNuevaLocalizacion(){
        try {
            localizacionNueva = new Localizacion(pasarListaObjetoAID(id_objeto.getSelectionModel().getSelectedItem()), //TODO SACAR VALOR DEL COMBO
                                                pasarListaHabitacionAID(id_habitacion.getSelectionModel().getSelectedItem()), //TODO SACAR VALOR DEL COMBO
                                                Integer.parseInt(posX.getText()),
                                                Integer.parseInt(posY.getText()),
                                                check_interactuable.isSelected());
            System.out.println("Id_Objeto "+localizacionNueva.getObjeto()+" - Id_Habitación "+localizacionNueva.getHabitacion());

            enviarLocalizacion();
        } catch (Exception e) {
            System.out.println("Error creando Localización");
        }
    }

    private boolean comprobarDatos(){
        //TODO
        boolean resultado=true;
        return resultado;
    }

    @FXML
    void Cancelar(ActionEvent event) {
        //Cerrar ventana
        Stage primaryStage = (Stage) this.id_objeto.getScene().getWindow();
        primaryStage.close();
    }

    //AUXILIARES
    private void cargarDatos(){
        check_interactuable.setSelected(localizacion.getInteractuable());
        posX.setText(""+localizacion.getPosicion_x());
        posY.setText(""+localizacion.getPosicion_y());

        
    }

    public void setEditar(boolean editar){
        this.editar = editar;
    }

    public void setLocalizacion(Localizacion localizacion){
        this.localizacion = localizacion;       
        if(editar){
            cargarDatos();
        } 
    }

    public void setEnlace(MainController mainController){
        this.mainController = mainController;
        rellenarCombo();
    }

    private void enviarLocalizacion(){
        if(mainController!=null){
            this.mainController.recibirLocalizacion(localizacionNueva);
        }
    }

    @FXML
    private void abrirBuscadorHabitaciones(){
        //TODO buscador
    }

    @FXML
    private void abrirBuscadorObjetos(){
        //TODO buscador
    }

    private void rellenarCombo(){
        //TODO RELLENAR COMBO
        listaHabitaciones.setAll(rellenarListaHabitaciones());
        listaObjetos.setAll(rellenarListaObjetos());

        id_habitacion.setItems(listaHabitaciones);
        System.out.println("Habitaciones cargadas");
        System.out.println("Lista Habitaciones: "+listaHabitaciones);

        id_objeto.setItems(listaObjetos);
        System.out.println("Objetos cargados");
        System.out.println("Lista Habitaciones: "+listaObjetos);

    }

    private int pasarListaHabitacionAID(String habitacion){
        int resultado = -1;

        try {
            String s = habitacion.substring(1, habitacion.indexOf(")"));
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("No se ha podido detectar el número");
        }

        return resultado;
    }

    private int pasarListaObjetoAID(String objeto){
        int resultado = -1;

        try {
            String s = objeto.substring(1, objeto.indexOf(")"));
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("No se ha podido detectar el número");
        }

        return resultado;
    }

    private ObservableList<String> rellenarListaObjetos(){
        ObservableList<Objeto> objetos = this.mainController.dameListaObjetos();

        ObservableList<String> listaVistaCombo = FXCollections.observableArrayList();
        
        try {
            for (int x = 0; x < objetos.size(); x++) {
                Objeto o = objetos.get(x);
                listaVistaCombo.add("("+o.getId_objeto()+") - "+o.getNombre_objeto());
            }
        } catch (Exception e) {
            System.out.println("Error recibiendo objetos");
        }
        
        return listaVistaCombo;
    }

    private ObservableList<String> rellenarListaHabitaciones(){
        ObservableList<Habitacion> habitaciones = this.mainController.dameListaHabitacion();

        ObservableList<String> listaVistaCombo = FXCollections.observableArrayList();
        
        try {
            for (int x = 0; x < habitaciones.size(); x++) {
                Habitacion h = habitaciones.get(x);
                listaVistaCombo.add("("+h.getId_Habitacion()+") - Número de habitación: "+h.getNum_Habitacion()+" Pasillo: "+h.getNumPasillo());
            }
        } catch (Exception e) {
            System.out.println("Error recibiendo habitaciones");
        }
        
        return listaVistaCombo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

