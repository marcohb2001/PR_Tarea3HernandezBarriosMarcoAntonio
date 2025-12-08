package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

/**
 * Pantalla de Preferencias de la aplicación.
 * Actualmente solo gestiona un ajuste:
 *  - Mostrar únicamente los productos disponibles en el listado.
 * La preferencia se guarda mediante SharedPreferences para que
 * el usuario la conserve incluso al cerrar la aplicación.
 */
public class Preferencias extends AppCompatActivity {

    private static final String NOMBRE_PREFS = "preferencias_app";
    private static final String CLAVE_SOLO_DISPONIBLES = "soloDisponibles";

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias);

        Toolbar toolbar = findViewById(R.id.toolbarPreferences);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        prefs = getSharedPreferences(NOMBRE_PREFS, MODE_PRIVATE);

        SwitchCompat swSoloDisponibles = findViewById(R.id.swSoloDisponibles);

        boolean estadoGuardado = prefs.getBoolean(CLAVE_SOLO_DISPONIBLES, false);
        swSoloDisponibles.setChecked(estadoGuardado);

        swSoloDisponibles.setOnCheckedChangeListener((buttonView, isChecked) -> guardarPreferencia(isChecked));
    }

    /**
     * Guarda el valor del interruptor en SharedPreferences.
     *
     * @param valor true si el usuario quiere mostrar solo productos disponibles
     */
    private void guardarPreferencia(boolean valor) {
        prefs.edit().putBoolean(CLAVE_SOLO_DISPONIBLES, valor).apply();
    }

    /**
     * Método estático de utilidad para obtener esta preferencia
     * desde cualquier parte de la aplicación, especialmente
     * desde ListadoProductos.
     *
     * @param context contexto que solicita la preferencia
     * @return true si el usuario activó "solo disponibles"
     */
    public static boolean getSoloDisponibles(android.content.Context context) {
        SharedPreferences p = context.getSharedPreferences(NOMBRE_PREFS, MODE_PRIVATE);
        return p.getBoolean(CLAVE_SOLO_DISPONIBLES, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
