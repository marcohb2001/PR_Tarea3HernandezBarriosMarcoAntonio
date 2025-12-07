package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla destinada a la inserción de nuevos productos en la base de datos.
 *
 * Permite introducir:
 *  - Nombre
 *  - Precio
 *  - Ingredientes
 *  - Peso neto en gramos
 *  - Disponible (checkbox)
 *
 * Al pulsar el botón "Guardar", se realiza un INSERT en la base de datos
 * mediante ProductoDAO y la actividad finaliza regresando al listado.
 */
public class InsertarProducto extends AppCompatActivity {

    private EditText txtNombre, txtPrecio, txtIngredientes, txtPeso;
    private CheckBox chkDisponible;
    private Button btnGuardar;

    private ProductoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar_producto);

        dao = new ProductoDAO(this);

        txtNombre = findViewById(R.id.txtNombreInsertar);
        txtPrecio = findViewById(R.id.txtPrecioInsertar);
        txtIngredientes = findViewById(R.id.txtIngredientesInsertar);
        txtPeso = findViewById(R.id.txtPesoInsertar);
        chkDisponible = findViewById(R.id.chkDisponibleInsertar);
        btnGuardar = findViewById(R.id.btnGuardarInsertar);

        btnGuardar.setOnClickListener(v -> guardarProducto());
    }

    /**
     * Recoge los datos introducidos por el usuario, realiza validaciones
     * y ejecuta el INSERT en la base de datos.
     */
    private void guardarProducto() {

        String nombre = txtNombre.getText().toString().trim();
        String precioStr = txtPrecio.getText().toString().trim();
        String ingredientes = txtIngredientes.getText().toString().trim();
        String pesoStr = txtPeso.getText().toString().trim();
        boolean disponible = chkDisponible.isChecked();

        if (nombre.isEmpty() || precioStr.isEmpty() || pesoStr.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        float precio;
        int peso;

        try {
            precio = Float.parseFloat(precioStr);
            peso = Integer.parseInt(pesoStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Formato numérico incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        Producto p = new Producto(disponible, nombre, precio, ingredientes, peso);

        long resultado = dao.insertarProducto(p);

        if (resultado > 0) {
            Toast.makeText(this, "El producto se ha insertado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al insertar: nombre duplicado", Toast.LENGTH_SHORT).show();
        }
    }
}

