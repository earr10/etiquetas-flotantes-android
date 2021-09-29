package com.example.etiquetasflotantes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilNombre;
    private TextInputLayout tilTelefono;
    private TextInputLayout tilDireccion;
    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoTelefono = (EditText) findViewById(R.id.campo_telefono);
        campoDireccion = (EditText) findViewById(R.id.campo_direccion);

        // Referencias TILs
        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilTelefono = (TextInputLayout) findViewById(R.id.til_telefono);
        tilDireccion = (TextInputLayout) findViewById(R.id.til_direccion);

        // Referencia Botón
        Button botonEnviar = (Button) findViewById(R.id.boton_enviar);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });

        campoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                esDireccionValido(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre inválido");
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches() || telefono.length() > 10) {
            tilTelefono.setError("Teléfono inválido");
            return false;
        } else {
            tilTelefono.setError(null);
        }

        return true;
    }

    private boolean esDireccionValido(String direccion) {
        Pattern patron = Pattern.compile("^[a-zA-Z0-9.,#&/+:; ]+$");
        if (!patron.matcher(direccion).matches() || direccion.length() > 50) {
            tilDireccion.setError("Dirección inválida");
            return false;
        } else {
            tilDireccion.setError(null);
        }

        return true;
    }

    private void validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();
        String direccion = tilDireccion.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esTelefonoValido(telefono);
        boolean c = esDireccionValido(direccion);

        if (a && b && c) {
            Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
        }

    }
}