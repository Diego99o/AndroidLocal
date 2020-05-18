package com.example.hoja.ui.dashboard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hoja.AdminSQLiteOpenHelper;
import com.example.hoja.Editar;
import com.example.hoja.MainActivity;
import com.example.hoja.R;

public class DashboardFragment extends Fragment {
    TextView nombre,apellido1,apellido2, estado,ciudad,direcion,nacimiento,numcelular;
    Bundle bb;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        nombre = root.findViewById(R.id.edit1);
        apellido1 = root.findViewById(R.id.edit2);
        apellido2 =root.findViewById(R.id.edit3);
        estado =root.findViewById(R.id.edit4);
        ciudad =root.findViewById(R.id.edit5);
        direcion =root.findViewById(R.id.edit6);
        nacimiento =root.findViewById(R.id.edit19);
        numcelular =root.findViewById(R.id.edit7);
        bb = getActivity().getIntent().getExtras();
        setHasOptionsMenu(true);

//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//
//            }
//        });
       Llenarvista();

        return root;
    }
    private void Llenarvista (){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getActivity(), "administrador", null, 1);
        SQLiteDatabase Basededatos = admin.getWritableDatabase();
        String idusuario = bb.getString("usuario");

        if (!idusuario.isEmpty()){
            Cursor fila = Basededatos.rawQuery("select nombre, papellido, sapellido, estado, ciudad, direccion, nacimiento,celular from personal where idusuario="+idusuario,null);
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
                Toast.makeText(getContext(), "No existe informacion", Toast.LENGTH_LONG).show();
                Basededatos.close();
            }

        }else{
            Toast.makeText(getContext(), "No existe informacion", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        getActivity().getMenuInflater().inflate(R.menu.overflow,menu);
//        return true;
        inflater.inflate(R.menu.overflow1, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem cerrar){
        int id = cerrar.getItemId();
        if(id==R.id.cerrar){
            Toast.makeText(getContext(), "Cerrar sesion", Toast.LENGTH_LONG).show();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getActivity(), "administrador", null, 1);
            SQLiteDatabase Basededatos = admin.getWritableDatabase();
            Basededatos.close();
            Intent ii = new Intent(getActivity(), MainActivity.class);
            startActivity(ii);
        }
        else if(id==R.id.edit){
            String idUsuario = bb.getString("usuario");
            Bundle t = new Bundle();
            t.putString("usuario", idUsuario);
            Toast.makeText(getContext(), "Id usuario: "+idUsuario, Toast.LENGTH_LONG).show();
            Intent c = new Intent(getContext(), Editar.class);
            c.putExtras(t);
            startActivity(c);
        }
        return super.onOptionsItemSelected(cerrar);
    }

}