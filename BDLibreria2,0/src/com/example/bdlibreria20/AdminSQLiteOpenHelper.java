package com.example.bdlibreria20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table usuarios(Documento integer primary key, Nombre text, NombreLibro text, Volumen integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnte, int versionNue) {
        bd.execSQL("drop table if exists usuarios");
        bd.execSQL("create table usuarios(Documento integer primary key, Nombre text, NombreLibro text, Volumen integer)");        
    }    
}