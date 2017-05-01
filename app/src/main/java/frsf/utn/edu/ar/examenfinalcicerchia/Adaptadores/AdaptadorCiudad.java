package frsf.utn.edu.ar.examenfinalcicerchia.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import frsf.utn.edu.ar.examenfinalcicerchia.Modelo.Ciudad;
import frsf.utn.edu.ar.examenfinalcicerchia.R;

/**
 * Created by Martin on 28/04/2017.
 */

public class AdaptadorCiudad extends ArrayAdapter<Ciudad> {


    private Ciudad[] datos;

    public AdaptadorCiudad(Context context, Ciudad[] datos) {
        super(context, R.layout.item_lista_ciudad, datos);
        this.datos = datos;
    }


    public AdaptadorCiudad(Context context, ArrayList<Ciudad> parmDatos) {
        super(context, R.layout.item_lista_ciudad, parmDatos);


        this.datos = new Ciudad[parmDatos.size() ];

        int i = 0;
        for (Ciudad c : parmDatos) {
            this.datos[i] = c;
            i++;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View item = inflater.inflate(R.layout.item_lista_ciudad, null);

        // Nombre Ciudad
        TextView tvCiudad = (TextView) item.findViewById(R.id.tvCiudad);
        tvCiudad.setText(datos[position].getNombre());

        // Temperatura
        TextView tvTemperatura = (TextView) item.findViewById(R.id.tvTemperatura);
        tvTemperatura.setText("Temp: "+ datos[position].getClima().getTemActual()+" °C");

        // Humedad
        TextView tvTitleHumedad = (TextView) item.findViewById(R.id.tvTitleHumedad);
        tvTitleHumedad.setText("Humedad");

        TextView tvHumedad = (TextView) item.findViewById(R.id.tvHumedad);
        tvHumedad.setText(datos[position].getClima().getHumedad() + " %");

        // Visibilidad
        TextView tvTitleVisibilidad = (TextView) item.findViewById(R.id.tvTitleVisibilidad);
        tvTitleVisibilidad.setText("Visibiliad");

        TextView tvVisibilidad = (TextView) item.findViewById(R.id.tvVisibilidad);
        tvVisibilidad.setText(datos[position].getClima().getVisibilidad());


        // Estado/descripcion del clima
        TextView tvDescrpcion = (TextView) item.findViewById(R.id.tvDescripcion);
        tvDescrpcion.setText(datos[position].getClima().getEstado());


        return (item);
    }





}
