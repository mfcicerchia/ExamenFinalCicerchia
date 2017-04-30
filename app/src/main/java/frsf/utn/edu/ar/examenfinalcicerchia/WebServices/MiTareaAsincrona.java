package frsf.utn.edu.ar.examenfinalcicerchia.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import frsf.utn.edu.ar.examenfinalcicerchia.DTOs.DTOclima;


/**
 * Created by Martin on 29/04/2017.
 */


public class MiTareaAsincrona extends AsyncTask<String, Integer, String> {

    Context contexto;
    static String temperatura;
    ProgressDialog pDialog;

    public MiTareaAsincrona(Context ctx) {
        this.contexto = ctx;
    }


    @Override
    protected void onPreExecute() {

        pDialog = ProgressDialog.show(contexto, "Por favor espere...", "Consultando clima de la Ciudad Seleccionada ...", true);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        //  String resultado;


        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = new URL(params[0]).openStream();

            if (inputStream != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = buffer.readLine()) != null)
                    result += line;

                inputStream.close();
            } else {
                // ERROR;
            }

        } catch (Exception e) {
            // ERROR;
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;


//
//        for(int i=1; i<=20; i++) {
//            tareaCorta();
//
//            publishProgress(i*10);
//
//            if(isCancelled())
//                break;
//        }
//
//        return resultado = "Texto Devuelto por AyncTask";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progreso = values[0].intValue();

        pDialog.setProgress(progreso);
        //pbarProgreso.setProgress(progreso);
    }


    @Override
    protected void onPostExecute(String texto) {
        pDialog.cancel();
        DTOclima.reset();

        // obtener temperatura
        try {

            JSONObject json = new JSONObject(texto);
            JSONObject jsonMain = json.getJSONObject("main");

            Log.v("Objeto JSON: ", jsonMain.toString());
            double temperaturaK = jsonMain.getDouble("temp");


            float temperaturaKFloat = ((int) temperaturaK * 100) / 100;
            float temperaturaC = (float) (temperaturaKFloat - 273.15);

            DTOclima.setTemperatura(String.valueOf(temperaturaC) + " °C");

        } catch (Exception e) {
            Toast.makeText(contexto, "Error al parsear Temperatura. " + e.getStackTrace(), Toast.LENGTH_LONG).show();
            Log.e("problema", e.getMessage());
        }


        // obtener temperatura
        try {
            JSONObject json = new JSONObject(texto);
            JSONArray jsonWeather = json.getJSONArray("weather");
            JSONObject jsonItem = jsonWeather.getJSONObject(0);
            String descripcion = jsonItem.getString("description");


            DTOclima.setDescripcion(descripcion);

        } catch (Exception e) {
            Toast.makeText(contexto, "Error al parsear Descripcion. " + e.getStackTrace(), Toast.LENGTH_LONG).show();
            Log.e("problema", e.getMessage());
        }

        /// obtengo la humedad
        try {
            JSONObject json = new JSONObject(texto);
            JSONObject jsonMain = json.getJSONObject("main");
            int humedad = jsonMain.getInt("humidity");

            DTOclima.setHumedad(String.valueOf(humedad) + "%");

        } catch (Exception e) {
            Log.e("problema", e.getMessage());
        }

        /// obtengo la visibilidad
        try {
            JSONObject json = new JSONObject(texto);
            double visibilidad = json.getDouble("visibility") / 1000;

            DTOclima.setVisibilidad(String.valueOf(visibilidad) + " Km");

        } catch (Exception e) {
            Log.e("problema", e.getMessage());
        } finally {
            Toast.makeText(contexto, DTOclima.getCiudad() + ": " + DTOclima.getTemperatura() + "\n" +
                    "Visibilidad: " + ": " + DTOclima.getVisibilidad() + "\n" +
                    "Humedad: " + ": " + DTOclima.getHumedad() + "\n" +
                    "Descripcion: " + ": " + DTOclima.getDescripcion(), Toast.LENGTH_SHORT).show();

//        for (Ciudad c : MainActivity.datos) {
//            if (c.getNombre() == DTOclima.getCiudad()) {
//                Toast.makeText(contexto, "La ciudad Existe en la lista", Toast.LENGTH_SHORT).show();
//                Clima nuevoClima = new Clima();
//                nuevoClima.setTemActual(Float.valueOf(DTOclima.getTemperatura()));
//                nuevoClima.setEstado(DTOclima.getDescripcion());
//                nuevoClima.setHumedad(Integer.valueOf(DTOclima.getHumedad()));
//                nuevoClima.setVisibilidad(DTOclima.getVisibilidad());
//
//                c.setClima(nuevoClima);
//            }
//        }

//            Ciudad ciu = new Ciudad(DTOclima.getCiudad());
//            Clima cli = new Clima(Float.valueOf(DTOclima.getTemperatura()),
//                                  DTOclima.getDescripcion(),
//                                  Float.valueOf(DTOclima.getHumedad()),
//                                  DTOclima.getVisibilidad());
//            ciu.setClima(cli);
//            MainActivity.datosActualizados.add(ciu);

        }


//        if(!resultado.isEmpty()) Toast.makeText(contexto, "Tarea finalizada!", Toast.LENGTH_LONG).show();
//        temperatura = resultado;
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(contexto, "Tarea cancelada!",
                Toast.LENGTH_SHORT).show();
    }

    private void tareaLarga() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    private void tareaCorta() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public static String getTemperatura() {
        return temperatura;
    }

    public static void setTemperatura(String temperatura) {
        MiTareaAsincrona.temperatura = temperatura;
    }
}