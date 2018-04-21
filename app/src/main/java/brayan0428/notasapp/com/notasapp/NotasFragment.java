package brayan0428.notasapp.com.notasapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import brayan0428.notasapp.com.notasapp.Adaptadores.NotasAdapter;
import brayan0428.notasapp.com.notasapp.Clases.Notas;


public class NotasFragment extends Fragment{
    RecyclerView recyclerViewNotas;
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
        createData();
        recyclerViewNotas = view.findViewById(R.id.recyclernotas);
        //LinearLayoutManager es el que nos permite administrar el modo en que se acomodan los datos en el RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNotas.setLayoutManager(linearLayoutManager);
        notasAdapter = new NotasAdapter(view.getContext(),notas);
        recyclerViewNotas.setAdapter(notasAdapter);
    }

    public void createData(){
        notas = new ArrayList<>();
        notas.add(new Notas(1,"Prueba","xD", FechaActual));
        notas.add(new Notas(2,"Prueba 2","xD 2", FechaActual));
        notas.add(new Notas(3,
                "Prueba 3",
                "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
                FechaActual));
    }
}
