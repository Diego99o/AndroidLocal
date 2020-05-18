package com.example.hoja.ui.notifications;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hoja.AdminSQLiteOpenHelper;
import com.example.hoja.MainActivity;
import com.example.hoja.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        setHasOptionsMenu(true);
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