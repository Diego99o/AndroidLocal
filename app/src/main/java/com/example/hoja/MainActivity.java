package com.example.hoja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoja.ui.dashboard.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    private EditText et_ucc,et_pass;
    private Cursor fila,filas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ucc= (EditText) findViewById(R.id.ucc);
        et_pass= (EditText) findViewById(R.id.pass);
        this.setTitle(R.string.act_1);
    }
    public void crear (View v){
        et_ucc.setText("");
        et_pass.setText("");
        Intent i = new Intent(this,CrearCuenta.class);
        startActivity(i);
    }

    public void entrar (View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administrador", null, 1);
        SQLiteDatabase Basededatos = admin.getWritableDatabase();
        String ucc = et_ucc.getText().toString();
        String pass = et_pass.getText().toString();
        if (!ucc.isEmpty() && !pass.isEmpty()){
            fila = Basededatos.rawQuery("select u.id, u.ucc, u.contrasena, p.id  from usuario u left join personal p on u.id = p.idusuario  where ucc='"+ ucc +"' and contrasena='"+ pass +"'",null);
            if (fila.moveToFirst() == true){
                String idUsuario = fila.getString(0);
                String uccc = fila.getString(1);
                String passw = fila.getString(2);
                String idPersona = fila.getString(3);
                if ((ucc.equals(uccc)) && (pass.equals(passw))){
                    et_ucc.setText("");
                    et_pass.setText("");
                    if(idPersona == null){
                        Bundle d = new Bundle();
                        d.putString("usuario", idUsuario);
                        Intent f = new Intent(this,FormatoHoja.class);
                        f.putExtras(d);
                        startActivity(f);
                    }
                    else{
                        Bundle d = new Bundle();
                        d.putString("usuario", idUsuario);
                        Intent i = new Intent(this, Ingreso.class);
                        i.putExtras(d);
                        startActivity(i);
                    }
                }
                else{
                    Toast.makeText(this, "El cédula o contraseña es incorrecta.", Toast.LENGTH_LONG).show();
                    Basededatos.close();
                }
            }
            else{
                Toast.makeText(this, "El cédula o contraseña es incorrecta", Toast.LENGTH_LONG).show();
                Basededatos.close();
            }
        }
        else{
            Toast.makeText(this, "Debes introducir cédula y contraseña.", Toast.LENGTH_LONG).show();
            Basededatos.close();
        }

    }
}
