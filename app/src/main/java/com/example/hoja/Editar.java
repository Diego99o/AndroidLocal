package com.example.hoja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    private EditText nombre,apellido1,apellido2, estado,ciudad,direcion,nacimiento,numcelular;
    Bundle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        nombre = (EditText) findViewById(R.id.edit1);
        apellido1 = (EditText)findViewById(R.id.edit2);
        apellido2 =(EditText)findViewById(R.id.edit3);
        estado =(EditText)findViewById(R.id.edit4);
        ciudad =(EditText)findViewById(R.id.edit5);
        direcion =(EditText)findViewById(R.id.edit6);
        nacimiento =(EditText)findViewById(R.id.edit19);
        numcelular =(EditText)findViewById(R.id.edit7);
        t = getIntent().getExtras();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administrador", null, 1);
        SQLiteDatabase Basededatos = admin.getWritableDatabase();
        String idUsuario = t.getString("usuario");
        Cursor fila = Basededatos.rawQuery("select nombre, papellido, sapellido, estado, ciudad, direccion, nacimiento, celular  from personal where idusuario="+idUsuario,null);
        if (fila.moveToFirst()){
            nombre.setText(fila.getString(0));
            apellido1.setText(fila.getString(1));
            apellido2.setText(fila.getString(2));
            estado.setText(fila.getString(3));
            ciudad.setText(fila.getString(4));
            direcion.setText(fila.getString(5));
            nacimiento.setText(fila.getString(6));
            numcelular.setText(fila.getString(7));
            Basededatos.close();
        }
        else{
            Toast.makeText(this, "No existe articulo", Toast.LENGTH_LONG).show();
            Basededatos.close();
        }

    }
    public  void modificar (View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administrador", null, 1);
        SQLiteDatabase Basededatos = admin.getWritableDatabase();
        String idUsuario = t.getString("usuario");
        String nombre1 = nombre.getText().toString();
        String apellido11 = apellido1.getText().toString();
        String apellido22 = apellido2.getText().toString();
        String estado1 = estado.getText().toString();
        String ciudad1 = ciudad.getText().toString();
        String direccion1 = direcion.getText().toString();
        String nacimiento1 = nacimiento.getText().toString();
        String celular = numcelular.getText().toString();
        if (!nombre1.isEmpty() && !apellido11.isEmpty() && !apellido22.isEmpty() && !estado1.isEmpty()&& !ciudad1.isEmpty()&& !direccion1.isEmpty()&& !nacimiento1.isEmpty()&& !celular.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("idusuario", idUsuario);
            registro.put("nombre", nombre1);
            registro.put("papellido", apellido11);
            registro.put("sapellido", apellido22);
            registro.put("estado", estado1);
            registro.put("ciudad", ciudad1);
            registro.put("direccion", direccion1);
            registro.put("nacimiento", nacimiento1);
            registro.put("celular", celular);
            int cantidad = Basededatos.update("personal",registro,"idusuario="+idUsuario,null);
            Basededatos.close();
            if (cantidad==1){
                Toast.makeText(this, "Se ha modificado", Toast.LENGTH_LONG).show();
                Bundle p = new Bundle();
                p.putString("usuario", idUsuario);
                Intent cc = new Intent(this, Ingreso.class);
                cc.putExtras(p);
                startActivity(cc);
            }
            else{
                Toast.makeText(this, "Error en la modificacion", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem cerrar){
        int id = cerrar.getItemId();
        if(id==R.id.cerrar){
            Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_LONG).show();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administrador", null, 1);
            SQLiteDatabase Basededatos = admin.getWritableDatabase();
            Basededatos.close();
            finish();
            Intent ii = new Intent(this,MainActivity.class);
            startActivity(ii);
        }
        return super.onOptionsItemSelected(cerrar);
    }
}
