package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activity principal de la aplicación.
 * Si el usuario intenta acceder al listado sin productos cargados,
 * se le mostrará un mensaje indicando que primero debe insertar productos.
 */
public class MainActivity extends AppCompatActivity {

    private ProductoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        dao = new ProductoDAO(this);

        ImageButton boton = findViewById(R.id.imageButton);

        boton.setOnClickListener(v -> {

            if (dao.obtenerTodos().isEmpty()) {
                Toast.makeText(this, "Debe cargar productos antes de continuar", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, ListadoProductos.class);
            startActivity(intent);
        });
    }

    /**
     * Infla el menú en el Action Bar
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Gestiona los clics en el menú
     */
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_preferencias) {
            startActivity(new Intent(this, Preferencias.class));
            return true;
        }
        if (id == R.id.menu_insertar) {
            startActivity(new Intent(this, InsertarProducto.class));
            return true;
        }
        if (id == R.id.menu_eliminar) {
            startActivity(new Intent(this, EliminarProducto.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
