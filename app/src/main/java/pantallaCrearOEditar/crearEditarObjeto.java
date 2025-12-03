package pantallaCrearOEditar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pantallaPrincipal.MainController;
import pantallaPrincipal.Objeto;

public class crearEditarObjeto implements Initializable{
    private boolean editar = true;
    private Objeto objeto = null;
    
    private MainController mainController;
    private Objeto objetoNuevo;

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
        if(comprobarDatos()){
            crearNuevoObjeto();

            //ALERT EXITO
            Alert alertExito=new Alert(AlertType.INFORMATION);
            alertExito.setTitle("Éxito");

            if(this.editar){
                alertExito.setHeaderText("Edición completada");
                alertExito.setContentText("Se ha editado el objeto");
            }else{
                alertExito.setHeaderText("Creación completada");
                alertExito.setContentText("Se ha creado un nuevo objeto");
            }

            alertExito.showAndWait();

            //CERRAR STAGE
            Stage primaryStage = (Stage) this.nombre_objeto.getScene().getWindow();
            primaryStage.close();
        }else{
            Alert alertError=new Alert(AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Datos no válidos");
            alertError.setContentText("Edite los datos en un formato válido...");
            alertError.showAndWait();
        }
    }

    private void crearNuevoObjeto(){
        try {
            objetoNuevo = new Objeto(0,
                                        nombre_objeto.getText().toUpperCase(),
                                        archivo.getText().toUpperCase(),
                                        descripcion.getText().toUpperCase(),
                                        Integer.parseInt(altura.getText()),
                                        Integer.parseInt(anchura.getText()));
            System.out.println(objetoNuevo.getId_objeto()+" - nombre "+objetoNuevo.getNombre_objeto());

        enviarObjeto();
        } catch (Exception e) {
            System.out.println("Error creando Objeto");
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        //Cerrar ventana
        Stage primaryStage = (Stage) this.nombre_objeto.getScene().getWindow();
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

    private void cargarDatos(){
        altura.setText(""+objeto.getAlto());
        anchura.setText(""+objeto.getAncho());
        id_objeto.setText(""+objeto.getId_objeto());
        nombre_objeto.setText(objeto.getNombre_objeto());
        descripcion.setText(objeto.getDescripcion());
        archivo.setText(objeto.getArchivo_objeto());

    }

    public void setObjeto(Objeto objeto){
        this.objeto = objeto;
        
        if(editar){
            cargarDatos();
        }
    }

    public void setEnlace(MainController mainController){
        this.mainController = mainController;
    }

    private void enviarObjeto(){
        if(mainController!=null){
            this.mainController.recibirObjeto(objetoNuevo);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
