package com.example.hoja.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.hoja.R;

public class DashboardFragment extends Fragment {
    EditText nombre,apellido1,apellido2, numcedula,carrera,semestre,numcelular;
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
        numcedula =root.findViewById(R.id.edit4);
        carrera =root.findViewById(R.id.edit5);
        semestre =root.findViewById(R.id.edit6);
        numcelular =root.findViewById(R.id.edit7);
        bb = getActivity().getIntent().getExtras();

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
        String cedula = bb.getString("cedula");

        if (!idusuario.isEmpty()){
            Cursor fila = Basededatos.rawQuery("select nombre, papellido, sapellido, cedula, carrera, semestre,celular from personal where idusuario="+idusuario,null);
            if (fila.moveToFirst()){
                nombre.setText(fila.getString(0));
                apellido1.setText(fila.getString(1));
                apellido2.setText(fila.getString(2));
                numcedula.setText(fila.getString(3));
                carrera.setText(fila.getString(4));
                semestre.setText(fila.getString(5));
                numcelular.setText(fila.getString(6));
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

}