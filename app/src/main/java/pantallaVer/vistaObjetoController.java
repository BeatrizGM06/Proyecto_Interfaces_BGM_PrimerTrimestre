package pantallaVer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import pantallaPrincipal.Objeto;

public class vistaObjetoController implements Initializable {
    private Objeto objeto;

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

    public void setObjeto(Objeto objeto){
        this.objeto=objeto;

        cargarDatos();
    }

    private void cargarDatos(){
        altura.setText(""+objeto.getAlto());
        anchura.setText(""+objeto.getAncho());
        id_objeto.setText(""+objeto.getId_objeto());
        nombre_objeto.setText(objeto.getNombre_objeto());
        descripcion.setText(objeto.getDescripcion());
        archivo.setText(objeto.getArchivo_objeto());

        cargarImagen();
    }

    //TODO
    private void cargarImagen(){
        boolean condicion = false;
        if(condicion){
            imagen.setImage(null); //TODO RELLENAR IMAGEN
        }else{
            System.out.println("No se encontró la imagen");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
