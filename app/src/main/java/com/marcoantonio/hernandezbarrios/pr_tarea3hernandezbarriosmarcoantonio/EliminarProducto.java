package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Pantalla destinada a eliminar varios productos a la vez.
 *
 * Funciones:
 *  - Mostrar una lista con checkboxes
 *  - Eliminar todos los seleccionados al pulsar un botón
 *  - Permitir volver sin eliminar nada
 */
public class EliminarProducto extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EliminarAdapter adapter;
    private ProductoDAO dao;
    private Button btnEliminar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_producto);

        dao = new ProductoDAO(this);

        recyclerView = findViewById(R.id.recyclerEliminar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnEliminar = findViewById(R.id.btnEliminarSeleccionados);
        btnVolver = findViewById(R.id.btnVolverEliminar);

        cargarListado();

        btnEliminar.setOnClickListener(v -> eliminarSeleccionados());
        btnVolver.setOnClickListener(v -> finish());
    }

    /**
     * Carga la lista de productos en el RecyclerView.
     */
    private void cargarListado() {
        List<Producto> productos = dao.obtenerTodos();

        if (productos.isEmpty()) {
            Toast.makeText(this, "No hay productos que eliminar", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        adapter = new EliminarAdapter(productos);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Elimina todos los productos que el usuario ha marcado
     * y regresa a la pantalla principal.
     */
    private void eliminarSeleccionados() {

        List<Producto> seleccionados = adapter.getSeleccionados();

        if (seleccionados.isEmpty()) {
            Toast.makeText(this, "No has seleccionado ningún producto", Toast.LENGTH_SHORT).show();
            return;
        }

        for (Producto p : seleccionados) {
            dao.eliminarProducto(p.getNombre());
        }

        if (seleccionados.size() == 1) {
            Toast.makeText(this, "El producto se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Los productos se han eliminado correctamente", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}

