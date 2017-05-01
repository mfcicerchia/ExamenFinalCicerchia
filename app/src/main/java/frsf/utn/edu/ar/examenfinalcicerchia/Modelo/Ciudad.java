package frsf.utn.edu.ar.examenfinalcicerchia.Modelo;

/**
 * Created by Martin on 27/04/2017.
 */

public class Ciudad {

    private int id;
    private String nombre;
    private String provincia;
    private String cp;
    private String lt;
    private String lg;
    private Clima clima;

    public Ciudad(int id, String nombre, Clima clima) {
        this.id = id;
        this.nombre = nombre;
        this.clima = clima;
    }

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad(int id, String nombre, String provincia, String cp, String lt, String lg, Clima clima) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.cp = cp;
        this.lt = lt;
        this.lg = lg;
        this.clima = clima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }


    public void updateCiudad(Ciudad ciu) {
        this.nombre = ciu.getNombre();
        this.clima = ciu.getClima();
    }
}
