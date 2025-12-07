package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla que muestra y permite modificar los datos de un producto.
 * Se accede desde ListadoProductos al pulsar sobre un elemento de la lista.
 */
public class FichaProducto extends AppCompatActivity {

    private EditText txtNombre, txtPrecio, txtIngredientes, txtPeso;
    private CheckBox chkDisponible;
    private Button btnGuardar, btnVolver;

    private ProductoDAO dao;
    private String nombreOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_producto);

        dao = new ProductoDAO(this);

        txtNombre = findViewById(R.id.txtNombreFicha);
        txtPrecio = findViewById(R.id.txtPrecioFicha);
        txtIngredientes = findViewById(R.id.txtIngredientesFicha);
        txtPeso = findViewById(R.id.txtPesoFicha);
        chkDisponible = findViewById(R.id.chkDisponibleFicha);
        btnGuardar = findViewById(R.id.btnGuardarFicha);
        btnVolver = findViewById(R.id.btnVolverFicha);

        nombreOriginal = getIntent().getStringExtra("nombreProducto");
        cargarDatosProducto(nombreOriginal);

        btnGuardar.setOnClickListener(v -> guardarCambios());
        btnVolver.setOnClickListener(v -> finish());
    }

    /**
     * Carga los datos del producto usando el DAO
     */
    private void cargarDatosProducto(String nombre) {
        Producto p = dao.obtenerPorNombre(nombre);
        if (p == null) {
            Toast.makeText(this, "Error al cargar producto", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtIngredientes.setText(p.getIngredientes());
        txtPeso.setText(String.valueOf(p.getPeso()));
        chkDisponible.setChecked(p.isDisponible());
    }

    /**
     * Guarda las modificaciones hechas al producto
     */
    private void guardarCambios() {

        String nombre = txtNombre.getText().toString().trim();
        String precioStr = txtPrecio.getText().toString().trim();
        String ingredientes = txtIngredientes.getText().toString().trim();
        String pesoStr = txtPeso.getText().toString().trim();
        boolean disponible = chkDisponible.isChecked();

        if (nombre.isEmpty() || precioStr.isEmpty() || pesoStr.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        float precio = Float.parseFloat(precioStr);
        int peso = Integer.parseInt(pesoStr);

        Producto p = new Producto(disponible, nombre, precio, ingredientes, peso);

        int resultado = dao.actualizarProducto(nombreOriginal, p);

        if (resultado > 0) {
            Toast.makeText(this, "Se ha actualizado el producto", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }
}

