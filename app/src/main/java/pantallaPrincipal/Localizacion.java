package pantallaPrincipal;
public class Localizacion {
    private int fk_objeto;
    private int fk_habitacion;
    private int posicion_x;
    private int posicion_y;
    private boolean interactuable;

    public Localizacion(int fk_objeto, int fk_habitacion, int posicion_x, int posicion_y, boolean interactuable){
        this.fk_objeto=fk_objeto;
        this.fk_habitacion=fk_habitacion;
        this.posicion_x=posicion_x;
        this.posicion_y=posicion_y;
        this.interactuable=interactuable;
    }

    public int getObjeto(){
        return fk_objeto;
    }

    public int getHabitacion(){
        return fk_habitacion;
    }

    public int getPosicion_x(){
        return posicion_x;
    }

    public int getPosicion_y(){
        return posicion_y;
    }

    public boolean getInteractuable(){
        return interactuable;
    }

    public void setObjeto(int fk_objeto){
        this.fk_objeto=fk_objeto;
    }

    public void setHabitacion(int fk_habitacion){
        this.fk_habitacion=fk_habitacion;
    }

    public void setPosicionX(int posicion_x){
        this.posicion_x=posicion_x;
    }

    public void setPosicionY(int posicion_y){
        this.posicion_y=posicion_y;
    }

    public void setInteractuable(boolean interactuable){
        this.interactuable=interactuable;
    }
}
