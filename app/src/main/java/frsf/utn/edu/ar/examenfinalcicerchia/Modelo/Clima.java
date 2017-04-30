package frsf.utn.edu.ar.examenfinalcicerchia.Modelo;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Martin on 27/04/2017.
 */

public class Clima {



    private Date fecha;
    private float temActual;
    private float max;
    private float min;
    private String descripcion; // ej: despejado, nubosidad variable
    private float st; // sensacion termica

    private String precipitaciones;
    private float presion;
    private String visibilidad;

    /// otros datos
    private int indiceUV;
    private float humedad;
    private int viento;
    private float puntoRocio;

    private Time salidaSol;
    private Time puestaSol;

    public Clima(){

    }

    public Clima(float temp, String descripcion, float humedad, String visibilidad){
        this.temActual = temp;
        this.descripcion = descripcion;
        this.humedad = humedad;
        this.visibilidad = visibilidad;
    }

    public Clima(float temActual, float st, String visibilidad) {
        this.temActual = temActual;
        this.st = st;
        this.visibilidad = visibilidad;
    }

    public Clima(float temActual) {
        this.temActual = temActual;
    }

    public Clima(float temp, String visibilidad, String descripcion, int humedad){
        this.temActual = temp;
        this.visibilidad = visibilidad;
        this.descripcion = descripcion;
        this.humedad = humedad;
    }

    public Clima(Date fecha, float temActual, int viento) {
        this.fecha = fecha;
        this.temActual = temActual;
        this.viento = viento;
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
