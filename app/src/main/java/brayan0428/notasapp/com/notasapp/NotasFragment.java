package brayan0428.notasapp.com.notasapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import brayan0428.notasapp.com.notasapp.Adaptadores.NotasAdapter;
import brayan0428.notasapp.com.notasapp.Clases.ConexionHelper;
import brayan0428.notasapp.com.notasapp.Clases.Notas;


public class NotasFragment extends Fragment{
    RecyclerView recyclerViewNotas;
    FloatingActionButton addNota;
    ArrayList<Notas> notas;
    NotasAdapter notasAdapter;
    Date Fecha = new Date();
    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    String FechaActual = formatoFecha.format(Fecha);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.notas_fragment,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        consultarNotas();
        recyclerViewNotas = view.findViewById(R.id.recyclernotas);
        addNota = view.findViewById(R.id.addNota);
        //LinearLayoutManager es el que nos permite administrar el modo en que se acomodan los datos en el RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNotas.setLayoutManager(linearLayoutManager);
        notasAdapter = new NotasAdapter(view.getContext(),notas);
        recyclerViewNotas.setAdapter(notasAdapter);

       addNota.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putInt("IdNota",0);
               bundle.putString("Titulo","");
               bundle.putString("Descripcion","");
               bundle.putString("Accion","Ins");
               FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               DetalleNotaFragment detalleNotaFragment = new DetalleNotaFragment();
               detalleNotaFragment.setArguments(bundle);
               fragmentTransaction.replace(R.id.fragment,detalleNotaFragment);
               fragmentTransaction.commit();
           }
       });
    }

    public void consultarNotas(){
        try{
            notas = new ArrayList<>();
            ConexionHelper conn = new ConexionHelper(getContext(),"bd_notas",null,1);
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from notas",null);
            if (cursor.moveToFirst()){
                while (cursor.moveToNext()){
                    notas.add(new Notas(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getString(3)));
                }
            }
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
