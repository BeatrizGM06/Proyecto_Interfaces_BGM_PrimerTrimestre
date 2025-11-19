package pantallaCrearOEditar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class crearEditarLocalizacion implements Initializable {
     @FXML
    private CheckBox check_interactuable;

    @FXML
    private TextField id_habitacion;

    @FXML
    private TextField id_objeto;

    @FXML
    private TextField posX;

    @FXML
    private TextField posY;

    @FXML
    void Aceptar(ActionEvent event) {
        //Comprobar datos y lanzar error o guardar
    }

    @FXML
    void Cancelar(ActionEvent event) {
        //Cerrar ventana
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Ver si esta creando o editando y cargar los datos si esta editando
    }
    
}
