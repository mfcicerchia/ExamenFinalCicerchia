package frsf.utn.edu.ar.examenfinalcicerchia.DTOs;

/**
 * Created by Martin on 29/04/2017.
 */
public class DTOclima {

    public static String ciudad;
    public static String temperatura;
    public static String visibilidad;
    public static String descripcion;
    public static String humedad;


    public static String getTemperatura() {
        return temperatura;
    }

    public static void setTemperatura(String temperatura) {
        DTOclima.temperatura = temperatura;
    }

    public static String getCiudad() {
        return ciudad;
    }

    public static void setCiudad(String ciudad) {
        DTOclima.ciudad = ciudad;
    }

    public static String getVisibilidad() {
        return visibilidad;
    }

    public static void setVisibilidad(String visibilidad) {
        DTOclima.visibilidad = visibilidad;
    }

    public static String getDescripcion() {
        return descripcion;
    }

    public static void setDescripcion(String descripcion) {
        DTOclima.descripcion = descripcion;
    }

    public static String getHumedad() {
        return humedad;
    }

    public static void setHumedad(String humedad) {
        DTOclima.humedad = humedad;
    }

    public static void reset(){
        DTOclima.setHumedad("0");
        DTOclima.setVisibilidad("0");
        DTOclima.setTemperatura("0");
        DTOclima.setDescripcion("sin datos");
    }
}



