package frsf.utn.edu.ar.examenfinalcicerchia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import frsf.utn.edu.ar.examenfinalcicerchia.Adaptadores.AdaptadorCiudad;
import frsf.utn.edu.ar.examenfinalcicerchia.DTOs.DTOclima;
import frsf.utn.edu.ar.examenfinalcicerchia.Modelo.Ciudad;
import frsf.utn.edu.ar.examenfinalcicerchia.Modelo.Clima;
import frsf.utn.edu.ar.examenfinalcicerchia.WebServices.MiTareaAsincrona;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    //    nombre
//    temperatura
//    sensacion termica
//    visibilidad
    final static String API_KEY = "2dbc54f6d493229a6986fad7d4b204e4";
    static ProgressDialog pDialog;
    int requestCode = 1;
    MiTareaAsincrona tarea1;
    public static ArrayList<Ciudad> datos = new ArrayList<Ciudad>();

    SharedPreferences prefs;

    int idCiudad =1;



    public static ArrayList<Ciudad> datosActualizados;

//datos de prueba
//private Ciudad[] datos = new Ciudad[]{
//new Ciudad(1, "Santa Fe", new Clima(10,8,10)),
//new Ciudad(2, "Buenos Aires", new Clima(18,17,7)),
//new Ciudad(3, "Cordoba", new Clima(15,12,11)),
//new Ciudad(3, "Rosario", new Clima(12,9,10))};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prefs = PreferenceManager.getDefaultSharedPreferences(this);


        // float temp, String visibilidad, String descripcion, int humedad


//        datos.add(new Ciudad(1, "Santa Fe", new Clima(10, "8", "despejado", 50)));
//        datos.add(new Ciudad(2, "Buenos Aires", new Clima(18, "17", "despejado", 7)));
//        datos.add(new Ciudad(3, "Cordoba", new Clima(15, "12.0", "despejado", 11)));
//        datos.add(new Ciudad(3, "Rosario", new Clima(12, "9", "despejado", 10)));

        ListView lvCiudades = (ListView) findViewById(R.id.lvCiudades);
//        cargarCiudadesAgregadas();

        this.refreshList();

        lvCiudades.setOnItemClickListener(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    /////////////////////////////////////////////////////////////////////////////////////
    /// Creacion del Menu Principal usando en la Action Bar
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ciudades, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.id_menu_item_nueva_ciudad:
                Intent i = new Intent(this, NuevaCiudadActivity.class);
                startActivityForResult(i, requestCode);

                Toast.makeText(this.getApplicationContext(), "Agregar una Ciudad", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_menu_item_sincronizar:

//                cargarCiudadesAgregadas();
//
//                for(Ciudad c: datos){
//                    callWebService(c.getNombre());
//                }
                this.refreshList();
                //Toast.makeText(this.getApplicationContext(), "Sincronizar con WebService - ACTUALIZACION MASIVA", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_menu_item_autor:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("CICERCHIA MARTIN FEDERICO \n" +
                        "email: mcicerchia@frsf.utn.edu.ar \n" +
                        "Cel: +5493426108610 \n")
                        .setTitle("Autor");
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // llamo al Web Service cuando pulso un item del list view
        DTOclima.reset();
        String ciudadSeleccionada = ((Ciudad) parent.getItemAtPosition(position)).getNombre();

        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ciudadSeleccionada+",ar&appid=2dbc54f6d493229a6986fad7d4b204e4";

        DTOclima.setCiudad(ciudadSeleccionada);
        tarea1 = new MiTareaAsincrona(MainActivity.this);
        tarea1.execute(url);
//        tarea1.actualizarVista();




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String nombreCiudad;
        nombreCiudad = data.getStringExtra("ciudad");

        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            Toast.makeText(this.getApplicationContext(), "Ciudad Agregada: " + nombreCiudad, Toast.LENGTH_SHORT).show();



           //datos.add(new Ciudad(idCiudad, nombreCiudad, new Clima(0, "0", "0", 0)));
            if (!existeCiudad(nombreCiudad) && nombreCiudad!="0") {
                idCiudad++;
                Ciudad c = new Ciudad(nombreCiudad);
                Clima cli = new Clima(0,"null","sin info",0);
                c.setClima(cli);
                datos.add(c);


                ///Grabarla en las SharedPreferences el nombre de la ciudad ingresada y una temperatura ficticia
                prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = prefs.edit();
                //editor.putFloat(nombreCiudad, 999); /// Lo que guardo en las shared preferences es "clave","valor"--> (nombreCidad,Temperatura)
                editor.putString(Integer.toString(idCiudad),nombreCiudad);
                editor.commit();

                callWebService(nombreCiudad);
            }

            ///Obtengo la temperatura de la ciudad agregada
            float temp = prefs.getFloat(nombreCiudad, 000); // nombre Ciudad - valor por defecto
            String ciudad = prefs.getString(Integer.toString(idCiudad),"sin datos");

            //Toast.makeText(this.getApplicationContext(), nombreCiudad + " - Temperatura: " + temp, Toast.LENGTH_SHORT).show();


            Log.v("Shared Preference Tam", Float.toString(temp));
            Log.i("Ciudad Agregada", ciudad);

            this.refreshList();
        } else {
            Toast.makeText(getApplicationContext(),
                    "No se realizo ninguna accion", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // Metodo para refrescar el ListView
    public  void refreshList() {

        ListView lvCiudades = (ListView) findViewById(R.id.lvCiudades);
        // actualizo la lista de la vista

        // invocamos la instancia del adaptador personalizado
        AdaptadorCiudad adaptador = new AdaptadorCiudad(this, this.datos);
        // asigno el adaptador al ListView
        lvCiudades.setAdapter(adaptador);

        lvCiudades.setOnItemClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        cargarCiudadesAgregadas();
        refreshList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //datos = new ArrayList<>();
    }

    private void cargarCiudadesAgregadas() {
        ///Grabarla en las SharedPreferences el nombre de la ciudad ingresada y una temperatura ficticia
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (datos != null) {


            int tam = prefs.getAll().size();
            idCiudad = tam;
            Toast.makeText(this.getApplicationContext(), "Ciudades Persistidas: " + Integer.toString(tam), Toast.LENGTH_SHORT).show();


            //int j=0;
            for (int i = 1; i <= tam; i++) {
                String nombreCiudad;
                nombreCiudad = prefs.getString(Integer.toString(i), "0");
                // Toast.makeText(this.getApplicationContext(), "Ciudad "+Integer.toString(i)+": "+ nombreCiudad, Toast.LENGTH_SHORT).show();

                if (!existeCiudad(nombreCiudad) && nombreCiudad!="0") {
                    Ciudad c = new Ciudad(nombreCiudad);
                    Clima cli = new Clima(0, "null", "sin info", 0);
                    c.setClima(cli);
                    datos.add(c);
                }
            }
        }
    }
    public static boolean existeCiudad(String nombreCiudad){
        if(datos!=null){
            for(Ciudad c: datos){
                if(c.getNombre()==nombreCiudad){
                    return true;
                }
            }
        }
        return false;
    }

    public static void actualizarDatosCiudad(){
        Ciudad ciu = new Ciudad(DTOclima.getCiudad());
        //float temp = Float.valueOf(DTOclima.getTemperatura());
        String visibilidad = DTOclima.getVisibilidad();
        String descripcion = DTOclima.getDescripcion();
        float temp = Float.valueOf(DTOclima.getTemperatura());
        float hum = Float.valueOf(DTOclima.getHumedad());

        Clima cli = new Clima(temp,visibilidad,descripcion,hum);
        ciu.setClima(cli);

        for(Ciudad c: datos) {
            if (c.getNombre() == ciu.getNombre()){
                Log.i("Existe Ciudad","La ciudad a actualizar fue encontrada. " +
                        "\n Su visibilidad es: "+visibilidad + "" +
                        "\n Descripcion: "+descripcion+"" +
                        "\n Temperatura: "+Float.toString(temp)+descripcion+"" +
                        "\n Humedad: "+Float.toString(hum));
                c.updateCiudad(ciu);
            }
        }
    }

    private void actualizarVista(){
        this.refreshList();
    }

    public void callWebService(String nombreCiudad){
        // llamo al Web Service cuando pulso un item del list view
        DTOclima.reset();
        String ciudadSeleccionada = nombreCiudad;

        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ciudadSeleccionada+",ar&appid=2dbc54f6d493229a6986fad7d4b204e4";

        DTOclima.setCiudad(ciudadSeleccionada);
        tarea1 = new MiTareaAsincrona(MainActivity.this);
        tarea1.execute(url);
    }

}
