package pantallaVer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class vistaObjetoController implements Initializable {
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
    private ImageView imagen;

    @FXML
    private TextField nombre_objeto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Cargar los datos
    }
    
}
