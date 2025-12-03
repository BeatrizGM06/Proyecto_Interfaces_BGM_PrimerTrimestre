package pantallaVer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import pantallaPrincipal.Habitacion;

public class vistaHabitacionController implements Initializable {
    private Habitacion habitacion;

    @FXML
    private CheckBox check_abierta;

    @FXML
    private TextField id_habitacion;

    @FXML
    private TextField nombre_emocion;

    @FXML
    private TextField num_habitacion;

    @FXML
    private TextField num_pasillo;
    
    public void setHabitacion(Habitacion habitacion){
        this.habitacion=habitacion;

        cargarDatos();
    }

    private void cargarDatos(){
        check_abierta.setSelected(habitacion.getAbierta());
        id_habitacion.setText(""+habitacion.getId_Habitacion());
        nombre_emocion.setText(habitacion.getFk_NombreEmocion());
        num_habitacion.setText(""+habitacion.getNum_Habitacion());
        num_pasillo.setText(""+habitacion.getNumPasillo());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Bloquear checkbox
        check_abierta.setDisable(true);
    }
    
}
