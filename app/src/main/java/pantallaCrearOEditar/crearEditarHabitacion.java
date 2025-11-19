package pantallaCrearOEditar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class crearEditarHabitacion implements Initializable {
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
