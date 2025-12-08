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

    private ObservableList<Integer> listaObjetos = FXCollections.observableArrayList();
    private ObservableList<Integer> listaHabitaciones = FXCollections.observableArrayList();

     @FXML
    private CheckBox check_interactuable;

    @FXML
    private ComboBox<Integer> id_habitacion;

    @FXML
    private ComboBox<Integer> id_objeto;

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
            localizacionNueva = new Localizacion(id_objeto.getSelectionModel().getSelectedItem(),
                                                id_habitacion.getSelectionModel().getSelectedItem(),
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

        if(localizacion!=null){
            int objeto = localizacion.getObjeto();
            int habitacion = localizacion.getHabitacion();

            int idxObj = encontrarObjeto(objeto);
            if (idxObj >= 0) {
                id_objeto.getSelectionModel().select(idxObj);
            }

            int idxHab = encontrarHabitacion(habitacion);
            if (idxHab >= 0) {
                id_habitacion.getSelectionModel().select(idxHab);
            }
        }
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
            this.mainController.recibirLocalizacion(localizacionNueva,localizacion,editar);
        }
    }

    private void rellenarCombo(){
        listaHabitaciones.setAll(rellenarListaHabitaciones());
        listaObjetos.setAll(rellenarListaObjetos());

        id_habitacion.setItems(listaHabitaciones);
        System.out.println("Habitaciones cargadas");
        System.out.println("Lista Habitaciones: "+listaHabitaciones);

        id_objeto.setItems(listaObjetos);
        System.out.println("Objetos cargados");
        System.out.println("Lista Habitaciones: "+listaObjetos);
    }

    private int encontrarHabitacion(int habitacion){
        System.out.println("Buscando habitacion");
        int index = 0;
        for(int x=0; x<listaHabitaciones.size();x++){
            if(listaHabitaciones.get(x)==habitacion){
                index=x;
            }
        }
        System.out.println("Indice de la habitacion: "+habitacion);
        return index;
    }

    private int encontrarObjeto(int objeto){
        System.out.println("Buscando objeto");
        int index = 0;
        for(int x=0; x<listaObjetos.size();x++){
            if(listaObjetos.get(x)==objeto){
                index=x;
            }
        }
        System.out.println("Indice del objeto: "+index);
        return index;
    }

    private ObservableList<Integer> rellenarListaObjetos(){
        ObservableList<Objeto> objetos = this.mainController.dameListaObjetos();

        ObservableList<Integer> objetosID = FXCollections.observableArrayList();
        try {
            for (int x = 0; x < objetos.size(); x++) {
                Objeto o = objetos.get(x);
                objetosID.add(o.getId_objeto());
            }
        } catch (Exception e) {
            System.out.println("Error recibiendo objetos");
        }
        
        return objetosID;
    }

    private ObservableList<Integer> rellenarListaHabitaciones(){ 
        ObservableList<Habitacion> habitaciones = this.mainController.dameListaHabitacion();


        ObservableList<Integer> habitacionesID = FXCollections.observableArrayList();
        try {
            for (int x = 0; x < habitaciones.size(); x++) {
                Habitacion h = habitaciones.get(x);
                habitacionesID.add(h.getId_Habitacion());
            }
        } catch (Exception e) {
            System.out.println("Error recibiendo habitaciones");
        }

        return habitacionesID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

