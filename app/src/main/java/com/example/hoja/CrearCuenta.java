package com.example.hoja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CrearCuenta extends AppCompatActivity {

    EditText et_iducc,et_contrasena;
    CheckBox check1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        et_iducc = (EditText)findViewById(R.id.iducc);
        et_contrasena = (EditText)findViewById(R.id.passw);
        check1 = (CheckBox) findViewById(R.id.checkBox);
        this.setTitle(R.string.act_2);
    }
    public void registrar (View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administrador", null, 1);
        SQLiteDatabase Basededatos = admin.getWritableDatabase();
        String iducc = et_iducc.getText().toString();
        String contrasena = et_contrasena.getText().toString();
        if (!iducc.isEmpty() && !contrasena.isEmpty() && check1.isChecked()){
            ContentValues registro = new ContentValues();
            registro.put("ucc", iducc);
            registro.put("contrasena", contrasena);
            Basededatos.insert("usuario", null,registro );
            Basededatos.close();
            et_iducc.setText("");
            et_contrasena.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
