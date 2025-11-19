package pantallaCrearOEditar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class crearEditarObjeto implements Initializable{
    @FXML
    private TextField altura;

    @FXML
    private TextField anchura;

    @FXML
    private TextField archivo;

    @FXML
    private TextArea descripcion;

    @FXML
    private TextField id_objeto;

    @FXML
    private TextField nombre_objeto;

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
