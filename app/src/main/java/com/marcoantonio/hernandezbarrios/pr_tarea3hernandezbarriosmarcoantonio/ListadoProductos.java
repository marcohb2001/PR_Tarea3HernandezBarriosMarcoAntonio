package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Actividad encargada de mostrar el listado de productos del supermercado.
 * Los datos se cargan desde la base de datos mediante ProductoDAO.
 *
 * Funcionalidades:
 *  - Mostrar los productos en un RecyclerView.
 *  - Permitir al usuario abrir la pantalla FichaProducto al pulsar un elemento.
 *  - Recargar los datos al volver, por si un producto fue modificado.
 *  - Aplicar las preferencias del usuario (más adelante se añadirá filtrado).
 */
public class ListadoProductos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductosAdapter adapter;
    private ProductoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_productos);

        Toolbar toolbar = findViewById(R.id.toolbarListado);
        setSupportActionBar(toolbar);

        dao = new ProductoDAO(this);

        recyclerView = findViewById(R.id.recyclerProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarListado();
    }

    /**
     * Carga los productos desde la base de datos aplicando las preferencias del usuario.
     * Si la opción "Mostrar solo productos disponibles" está activada, se filtrarán
     * únicamente los productos disponibles. En caso contrario, se mostrarán todos.
     */
    private void cargarListado() {

        boolean soloDisponibles = Preferencias.getSoloDisponibles(this);

        List<Producto> productos = soloDisponibles
                ? dao.obtenerDisponibles()
                : dao.obtenerTodos();

        if (productos.isEmpty()) {
            Toast.makeText(this, "No hay productos que mostrar", Toast.LENGTH_SHORT).show();
        }

        adapter = new ProductosAdapter(productos, producto -> {
            Intent i = new Intent(ListadoProductos.this, FichaProducto.class);
            i.putExtra("nombreProducto", producto.getNombre());
            startActivity(i);
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * Se ejecuta cada vez que el usuario vuelve a esta pantalla.
     * Recarga los productos para reflejar cambios realizados en FichaProducto.
     */
    @Override
    protected void onResume() {
        super.onResume();
        cargarListado();
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
