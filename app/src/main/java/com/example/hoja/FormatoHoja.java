package com.example.hoja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoja.ui.dashboard.DashboardFragment;

public class FormatoHoja extends AppCompatActivity {
    EditText et_nombre,et_papellido,et_sapellido,et_estado,et_ciudad,et_direccion,et_nacimiento,et_celular;
    private Cursor fila;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato_hoja2);
        this.setTitle(R.string.act_3);
        et_nombre=(EditText) findViewById(R.id.edit1);
        et_papellido =(EditText) findViewById(R.id.edit2);
        et_sapellido =(EditText) findViewById(R.id.edit3);
        et_estado =(EditText)findViewById(R.id.edit4);
        et_ciudad =(EditText) findViewById(R.id.edit5);
        et_direccion =(EditText) findViewById(R.id.edit6);
        et_nacimiento =(EditText) findViewById(R.id.edit19);
        et_celular =(EditText) findViewById(R.id.edit7);
        b = getIntent().getExtras();
    }
    public void guardar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administrador", null, 1);
        SQLiteDatabase Basededatos = admin.getWritableDatabase();
        String nombre = et_nombre.getText().toString();
        String p_apellido = et_papellido.getText().toString();
        String s_apellido = et_sapellido.getText().toString();
        String estado = et_estado.getText().toString();
        String ciudad = et_ciudad.getText().toString();
        String direccion = et_direccion.getText().toString();
        String nacimiento = et_nacimiento.getText().toString();
        String celular = et_celular.getText().toString();
        String idUsuario = b.getString("usuario");
        if (!nombre.isEmpty() && !p_apellido.isEmpty() && !s_apellido.isEmpty() && !estado.isEmpty() && !ciudad.isEmpty() && !direccion.isEmpty() && !nacimiento.isEmpty() && !celular.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("papellido", p_apellido);
            registro.put("sapellido", s_apellido);
            registro.put("estado", estado);
            registro.put("ciudad", ciudad);
            registro.put("direccion", direccion);
            registro.put("nacimiento", nacimiento);
            registro.put("celular", celular);
            registro.put("idusuario", idUsuario);
            Basededatos.insert("personal", null,registro );
            Basededatos.close();
            Toast.makeText(this, "Guardado satisfactorio", Toast.LENGTH_LONG).show();
            et_nombre.setText("");
            et_papellido.setText("");
            et_sapellido.setText("");
            et_estado.setText("");
            et_ciudad.setText("");
            et_direccion.setText("");
            et_nacimiento.setText("");
            et_celular.setText("");
            Bundle xx = new Bundle();
            xx.putString("usuario", idUsuario);
            Toast.makeText(this, "Id usuario: " + idUsuario, Toast.LENGTH_LONG).show();
            Intent z = new Intent(this, Ingreso.class);
            z.putExtras(xx);
            startActivity(z);


        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
