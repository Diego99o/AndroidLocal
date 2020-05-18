package com.example.hoja.ui.home;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hoja.AdminSQLiteOpenHelper;
import com.example.hoja.Ingreso;
import com.example.hoja.MainActivity;
import com.example.hoja.R;

public class HomeFragment extends Fragment {
    EditText empresa,sector,subsector,duracion,cargo,logros,nivel,telefono,ciudad;
    Bundle tt;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//
//            }
//        });
        empresa = root.findViewById(R.id.editText);
        sector = root.findViewById(R.id.editText2);
        subsector =root.findViewById(R.id.editText3);
        duracion =root.findViewById(R.id.editText4);
        cargo =root.findViewById(R.id.editText7);
        logros =root.findViewById(R.id.editText9);
        nivel =root.findViewById(R.id.editText8);
        telefono  =root.findViewById(R.id.editText12);
        ciudad  =root.findViewById(R.id.editText11);
        tt = getActivity().getIntent().getExtras();
        Button button = (Button) root.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( getActivity(), "administrador", null, 1);
                SQLiteDatabase Basededatos = admin.getWritableDatabase();
                String idUsuario = tt.getString("usuario");
                String empresa1 = empresa.getText().toString();
                String sector1 = sector.getText().toString();
                String subsector1 = subsector.getText().toString();
                String duracion1 = duracion.getText().toString();
                String cargo1 = cargo.getText().toString();
                String logros1 = logros.getText().toString();
                String nivel1 = nivel.getText().toString();
                String telefono1 = telefono.getText().toString();
                String ciudadex = ciudad.getText().toString();
                if (!empresa1.isEmpty() && !sector1.isEmpty() && !duracion1.isEmpty() && !cargo1.isEmpty() && !logros1.isEmpty() && !telefono1.isEmpty() && !ciudadex.isEmpty()){
                    ContentValues registro = new ContentValues();
                    registro.put("idusuario", idUsuario);
                    registro.put("empresa", empresa1);
                    registro.put("sector", sector1);
                    registro.put("subsector", subsector1);
                    registro.put("duracion", duracion1);
                    registro.put("cargo", cargo1);
                    registro.put("logros", logros1);
                    registro.put("nivel", nivel1);
                    registro.put("telefono", telefono1);
                    registro.put("ciudadex", ciudadex);
                    Basededatos.insert("experiencia", null,registro );
                    Basededatos.close();
                    Toast.makeText(getContext(), "Guardado satisfactorio", Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), "Idusuario: "+idUsuario, Toast.LENGTH_LONG).show();
                    empresa.setText("");
                    sector.setText("");
                    subsector.setText("");
                    duracion.setText("");
                    cargo.setText("");
                    logros.setText("");
                    nivel.setText("");
                    telefono.setText("");
                    ciudad.setText("");

                }else{
                    Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        getActivity().getMenuInflater().inflate(R.menu.overflow,menu);
//        return true;
        inflater.inflate(R.menu.overflow, menu);
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
        return super.onOptionsItemSelected(cerrar);
    }

}
