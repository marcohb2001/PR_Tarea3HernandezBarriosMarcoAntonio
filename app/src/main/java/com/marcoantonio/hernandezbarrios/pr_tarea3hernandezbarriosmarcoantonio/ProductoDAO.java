package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO encargada de gestionar todas las operaciones de base de datos
 * relacionadas con la tabla PRODUCTOS.
 *
 * En esta fase inicial del proyecto, el DAO solo implementa:
 *  - insertar un producto
 *  - eliminar un producto por su nombre
 *  - obtener la lista completa de productos
 *
 * Más adelante, si el proyecto lo requiere, se ampliarán estos métodos.
 */
public class ProductoDAO {

    private final SQLiteDatabase db;

    /**
     * Constructor.
     * Abre la base de datos en modo escritura a través del helper.
     *
     * @param context contexto de la aplicación
     */
    public ProductoDAO(Context context) {
        BaseDatosHelper helper = new BaseDatosHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * Inserta un nuevo producto en la tabla PRODUCTOS.
     *
     * @param p objeto Producto con los datos a insertar
     * @return id del registro insertado, o -1 si ha ocurrido un error
     */
    public long insertarProducto(Producto p) {
        ContentValues valores = new ContentValues();
        valores.put("disponible", p.isDisponible() ? 1 : 0);
        valores.put("nombre", p.getNombre());
        valores.put("precio", p.getPrecio());
        valores.put("ingredientes", p.getIngredientes());
        valores.put("peso", p.getPeso());

        return db.insert("PRODUCTOS", null, valores);
    }

    /**
     * Elimina un producto por su nombre.
     *
     * @param nombre nombre del producto a eliminar
     * @return número de registros eliminados (0 si no existía)
     */
    public int eliminarProducto(String nombre) {
        return db.delete("PRODUCTOS", "nombre=?", new String[]{nombre});
    }

    /**
     * Obtiene todos los productos almacenados en la tabla PRODUCTOS.
     *
     * @return lista de objetos Producto
     */
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT disponible, nombre, precio, ingredientes, peso FROM PRODUCTOS", null);

        if (c.moveToFirst()) {
            do {
                boolean disponible = c.getInt(0) == 1;
                String nombre = c.getString(1);
                float precio = c.getFloat(2);
                String ingredientes = c.getString(3);
                int peso = c.getInt(4);

                lista.add(new Producto(disponible, nombre, precio, ingredientes, peso));
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }

    /**
     * Obtiene un producto por su nombre.
     */
    public Producto obtenerPorNombre(String nombreBuscado) {
        Cursor c = db.rawQuery(
                "SELECT disponible, nombre, precio, ingredientes, peso FROM PRODUCTOS WHERE nombre=?",
                new String[]{nombreBuscado}
        );

        if (!c.moveToFirst()) {
            c.close();
            return null;
        }

        boolean disponible = c.getInt(0) == 1;
        String nombre = c.getString(1);
        float precio = c.getFloat(2);
        String ingredientes = c.getString(3);
        int peso = c.getInt(4);

        c.close();
        return new Producto(disponible, nombre, precio, ingredientes, peso);
    }

    /**
     * Actualiza los datos de un producto.
     */
    public int actualizarProducto(String nombreOriginal, Producto p) {
        ContentValues valores = new ContentValues();
        valores.put("disponible", p.isDisponible() ? 1 : 0);
        valores.put("nombre", p.getNombre());
        valores.put("precio", p.getPrecio());
        valores.put("ingredientes", p.getIngredientes());
        valores.put("peso", p.getPeso());

        return db.update("PRODUCTOS", valores, "nombre=?", new String[]{nombreOriginal});
    }

    /**
     * Obtiene únicamente los productos que están disponibles.
     *
     * @return lista de productos donde disponible = true
     */
    public List<Producto> obtenerDisponibles() {
        List<Producto> lista = new ArrayList<>();

        Cursor c = db.rawQuery(
                "SELECT disponible, nombre, precio, ingredientes, peso FROM PRODUCTOS WHERE disponible=1",
                null
        );

        if (c.moveToFirst()) {
            do {
                boolean disponible = true;
                String nombre = c.getString(1);
                float precio = c.getFloat(2);
                String ingredientes = c.getString(3);
                int peso = c.getInt(4);

                lista.add(new Producto(disponible, nombre, precio, ingredientes, peso));
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }

}

