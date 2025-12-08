package pantallaCrearOEditar;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pantallaPrincipal.Habitacion;
import pantallaPrincipal.MainController;

public class crearEditarHabitacion implements Initializable {
    private boolean editar=true;
    private Habitacion habitacion=null;
    private MainController mainController;
    private Habitacion habitacionNueva;

    @FXML
    private CheckBox check_abierta;

    @FXML
    private TextArea descripcion;

    @FXML
    private TextField id_habitacion;

    @FXML
    private TextField nombre_emocion;

    @FXML
    private TextField num_habitacion;

    @FXML
    private TextField num_pasillo;

    @FXML
    void Aceptar(ActionEvent event) {
        //TODO Comprobar datos y lanzar error o guardar
        if(comprobarDatos()){
            crearNuevaHabitacion();

            //ALERT EXITO
            Alert alertExito=new Alert(AlertType.INFORMATION);
            alertExito.setTitle("Éxito");
            if(this.editar){
                alertExito.setHeaderText("Edición completada");
                alertExito.setContentText("Se ha editado la habitación");
            }else{
                alertExito.setHeaderText("Creación completada");
                alertExito.setContentText("Se ha creado una nueva habitación");
            }
            
            alertExito.showAndWait();

            //CERRAR STAGE
            Stage primaryStage = (Stage) this.check_abierta.getScene().getWindow();
            primaryStage.close();
        }else{
            Alert alertError=new Alert(AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Datos no válidos");
            alertError.setContentText("Edite los datos en un formato válido...");
            alertError.showAndWait();
        }
    }

    private void crearNuevaHabitacion(){
        try {
            habitacionNueva = new Habitacion(habitacion==null?0:habitacion.getId_Habitacion(), //Un número cualquiera puesto que la base de datos cambiará el valor
                                            Integer.parseInt(num_habitacion.getText()),
                                            Integer.parseInt(num_pasillo.getText()),
                                            nombre_emocion.getText().toUpperCase(),
                                            check_abierta.isSelected());
            System.out.println(habitacionNueva.getId_Habitacion()+" - nombre "+habitacionNueva.getFk_NombreEmocion());

            enviarHabitacion();
        } catch (Exception e) {
            System.out.println(this.editar?"Error creando Habitación":
                                "Error modificando Habitación"
            );
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        //Cerrar ventana
        Stage primaryStage = (Stage) this.check_abierta.getScene().getWindow();
        primaryStage.close();
    }

    //AUXILIARES
    public boolean comprobarDatos(){
        //TODO
        boolean resultado=true;
        return resultado;
    }

    public void setEditar(boolean editar){
        this.editar = editar;
    }

    public void setHabitacion(Habitacion habitacion){
        this.habitacion = habitacion;

        if(editar){
            cargarDatos();
        }
    }

    private void cargarDatos(){
        check_abierta.setSelected(habitacion.getAbierta());
        id_habitacion.setText(""+habitacion.getId_Habitacion());
        nombre_emocion.setText(habitacion.getFk_NombreEmocion());
        num_habitacion.setText(""+habitacion.getNum_Habitacion());
        num_pasillo.setText(""+habitacion.getNumPasillo());
    }

    public void setEnlace(MainController mainController){
        this.mainController = mainController;
    }

    private void enviarHabitacion(){
        if(mainController!=null){
            this.mainController.recibirHabitacion(habitacionNueva,editar);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
