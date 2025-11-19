package pantallaPrincipal;
public class Habitacion {
    private int id_habitacion;
    private int num_habitacion;
    private int fk_numPasillo;
    private String fk_nombreEmocion;
    private boolean abierta;

    public Habitacion(int id_habitacion, int num_habitacion, int fk_numPasillo, String fk_nombreEmocion, boolean abierta){
        this.id_habitacion=id_habitacion;
        this.num_habitacion=num_habitacion;
        this.fk_numPasillo=fk_numPasillo;
        this.fk_nombreEmocion=fk_nombreEmocion;
        this.abierta=abierta;
    }

    public int getId_Habitacion(){
        return id_habitacion;
    }

    public int getNumPasillo(){
        return fk_numPasillo;
    }

    public int getNum_Habitacion(){
        return num_habitacion;
    }

    public String getFk_NombreEmocion(){
        return fk_nombreEmocion;
    }

    public boolean getAbierta(){
        return abierta;
    }
}
