package pantallaVer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class vistaHabitacionController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Cargar los datos
    }
    
}
