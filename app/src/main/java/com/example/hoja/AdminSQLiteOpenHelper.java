package com.example.hoja;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public  AdminSQLiteOpenHelper(Context context, String  name, SQLiteDatabase.CursorFactory factory, int version){
        super( context, name, factory, version);
    }


    public void onCreate(SQLiteDatabase Basededatos){
        Basededatos.execSQL("create table usuario(  id integer primary key autoincrement, ucc int, contrasena text)");
        Basededatos.execSQL("insert into usuario values(01,'12','admin')");
        Basededatos.execSQL("create table personal( id integer primary key autoincrement, idusuario integer, nombre text, papellido text, sapellido text, estado text,ciudad text, direccion text, nacimiento text, celular integer, foreign key(idusuario) references usuario(id))");
        Basededatos.execSQL("create table experiencia( id integer primary key autoincrement, idusuario integer, empresa text, sector text, subsector text, duracion text,cargo text, logros text, nivel text, telefono integer, ciudadex text, foreign key(idusuario) references usuario(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Basededatos, int i, int il){
        Basededatos.execSQL("create table usuario(  id integer primary key autoincrement, ucc int, contrasena text)");
        Basededatos.execSQL("insert into usuario values(01,'12','admin')");
    }
}
