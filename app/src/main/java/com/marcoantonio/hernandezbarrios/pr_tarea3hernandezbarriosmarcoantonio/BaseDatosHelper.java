package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que gestiona la creación y actualización de la base de datos local.
 *
 * La tabla PRODUCTOS contiene:
 *  - id (clave primaria autoincremental)
 *  - disponible (0/1)
 *  - nombre (texto, único)
 *  - precio (real)
 *  - ingredientes (texto)
 *  - peso (entero)
 */
public class BaseDatosHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "supermercado.db";
    private static final int VERSION = 1;

    public BaseDatosHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE PRODUCTOS (id INTEGER PRIMARY KEY AUTOINCREMENT, disponible INTEGER NOT NULL, nombre TEXT UNIQUE NOT NULL, precio REAL NOT NULL, ingredientes TEXT, peso INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        onCreate(db);
    }
}

