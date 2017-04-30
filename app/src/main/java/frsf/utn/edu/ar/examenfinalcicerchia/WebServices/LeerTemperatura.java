package frsf.utn.edu.ar.examenfinalcicerchia.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Martin on 29/04/2017.
 */

public class LeerTemperatura extends AsyncTask <String, Void, String> {

    //private ProgressDialog dialog;
    private ProgressDialog dialog;
    public Context contexto;


    public LeerTemperatura(Context contexto){
        this.contexto = contexto;
        this.dialog = new ProgressDialog(this.getContexto());
    }

    @Override
    protected void onPreExecute() {
        //dialog = ProgressDialog.show(MainActivity.this, "Por favor espere...", "Descargando clima...", true);
        this.dialog.setMessage("Cargando..., porfa espere! ");
        this.dialog.show();
        super.onPreExecute();
////        dialog = ProgressDialog.show(MainActivity.this, "Por favor espere...", "Descargando clima...", true);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Cargando..., porfa espere! ");
//        dialog.setIndeterminate(true);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = new URL(urls[0]).openStream();

            if(inputStream != null) {
                BufferedReader buffer = new BufferedReader( new InputStreamReader(inputStream));
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
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }
}
