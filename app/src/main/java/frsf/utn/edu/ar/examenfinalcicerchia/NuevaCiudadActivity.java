package frsf.utn.edu.ar.examenfinalcicerchia;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevaCiudadActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nueva_ciudad);

        Button btnNuevaCiudad = (Button) findViewById(R.id.btnNuevaCiudad);
        //Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnNuevaCiudad.setOnClickListener(this);
        //btnCancelar.setOnClickListener(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }




    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        int id = v.getId();

        switch (id) {

            case R.id.btnNuevaCiudad:
                EditText etNombreCiudad = (EditText) findViewById(R.id.etNombreCiudad);

                String nombreCiudad = etNombreCiudad.getText().toString().toUpperCase();

                if (nombreCiudad.length() != 0) {
                    // dao.crearCiudad(nombreCiudad);   /// 1er modificacion


                    // pasarle el nombre ingresado al MainActivity
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("ciudad", nombreCiudad);
                    setResult(RESULT_OK, i);
                    finish();

                    etNombreCiudad.clearComposingText();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "No ha introducido texto", Toast.LENGTH_SHORT)
                            .show();
                }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        setResult(RESULT_CANCELED,i);
    }
}
