package frsf.utn.edu.ar.examenfinalcicerchia.Modelo;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Martin on 27/04/2017.
 */

public class Clima {

    // unicas variables usadas para la resolucion del tp
    private float temActual;
    private String visibilidad;
    private String descripcion; // ej: despejado, nubosidad variable. El api provee los datos en ingles. Ej: clear Sky
    private float humedad;


    /// otras variables que podrian usarse en el modelo (no usados momentaneamente)
    private int indiceUV;
    private Date fecha;
    private float max;
    private float min;
    private float st; // sensacion termica
    private String precipitaciones;
    private float presion;
    private int viento;
    private float puntoRocio;
    private Time salidaSol;
    private Time puestaSol;


    public Clima(float temp, String visibilidad, String descripcion, float humedad) {
        this.temActual = temp;
        this.visibilidad = visibilidad;
        this.descripcion = descripcion;
        this.humedad = humedad;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTemActual() {
        return temActual;
    }

    public void setTemActual(float temActual) {
        this.temActual = temActual;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public String getEstado() {
        return descripcion;
    }

    public void setEstado(String estado) {
        this.descripcion = estado;
    }

    public float getSt() {
        return st;
    }

    public void setSt(float st) {
        this.st = st;
    }

    public String getPrecipitaciones() {
        return precipitaciones;
    }

    public void setPrecipitaciones(String precipitaciones) {
        this.precipitaciones = precipitaciones;
    }

    public float getPresion() {
        return presion;
    }

    public void setPresion(float presion) {
        this.presion = presion;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public int getIndiceUV() {
        return indiceUV;
    }

    public void setIndiceUV(int indiceUV) {
        this.indiceUV = indiceUV;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public int getViento() {
        return viento;
    }

    public void setViento(int viento) {
        this.viento = viento;
    }

    public float getPuntoRocio() {
        return puntoRocio;
    }

    public void setPuntoRocio(float puntoRocio) {
        this.puntoRocio = puntoRocio;
    }

    public Time getSalidaSol() {
        return salidaSol;
    }

    public void setSalidaSol(Time salidaSol) {
        this.salidaSol = salidaSol;
    }

    public Time getPuestaSol() {
        return puestaSol;
    }

    public void setPuestaSol(Time puestaSol) {
        this.puestaSol = puestaSol;
    }
}
