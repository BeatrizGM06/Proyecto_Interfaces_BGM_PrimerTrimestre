package pantallaVer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import pantallaPrincipal.Localizacion;

public class vistaLocalizacionController implements Initializable {
    private Localizacion localizacion=null;

    @FXML
    private CheckBox checkbox_interactuable;

    @FXML
    private TextField habitacion;

    @FXML
    private TextField objeto;

    @FXML
    private TextField posX;

    @FXML
    private TextField posY;

    public void setLocalizacion(Localizacion localizacion){
        this.localizacion=localizacion;
        
        cargarDatos();
    }

    private void cargarDatos(){
        
        checkbox_interactuable.setSelected(localizacion.getInteractuable());
        posX.setText(""+localizacion.getPosicion_x());
        posY.setText(""+localizacion.getPosicion_y());

        objeto.setText(""+localizacion.getObjeto());
        habitacion.setText(""+localizacion.getHabitacion());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
