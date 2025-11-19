package pantallaVer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class vistaLocalizacionController implements Initializable {
    @FXML
    private CheckBox checkbox_interactuable;

    @FXML
    private ChoiceBox<?> habitacion;

    @FXML
    private ChoiceBox<?> objeto;

    @FXML
    private TextField posX;

    @FXML
    private TextField posY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Cargar los datos
    }
    
}
