package pantallaPrincipal;
public class Objeto {
    private int id_objeto;
    private String nombre_objeto;
    private String archivo_objeto;
    private String descripcion;
    private int alto;
    private int ancho;

    public Objeto(int id_objeto, String nombre_objeto, String archivo_objeto, String descripcion, int alto, int ancho) {
        this.id_objeto = id_objeto;
        this.nombre_objeto = nombre_objeto;
        this.archivo_objeto = archivo_objeto;
        this.descripcion = descripcion;
        this.alto = alto;
        this.ancho = ancho;
    }

    public int getId_objeto() {
        return id_objeto;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
    }

    public String getNombre_objeto() {
        return nombre_objeto;
    }

    public void setNombre_objeto(String nombre_objeto) {
        this.nombre_objeto = nombre_objeto;
    }

    public String getArchivo_objeto() {
        return archivo_objeto;
    }

    public void setArchivo_objeto(String archivo_objeto) {
        this.archivo_objeto = archivo_objeto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}
