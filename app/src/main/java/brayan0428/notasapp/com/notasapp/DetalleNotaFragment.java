package brayan0428.notasapp.com.notasapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import brayan0428.notasapp.com.notasapp.Clases.ConexionHelper;
import brayan0428.notasapp.com.notasapp.Clases.Notas;

public class DetalleNotaFragment extends Fragment{
    int IdNota = 0;
    String Titulo;
    String Descripcion;
    String Accion;
    EditText titulo,descripcion;
    Button guardar,cancelar;

    Date Fecha = new Date();
    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    String FechaActual = formatoFecha.format(Fecha);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.detallenota_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IdNota = this.getArguments().getInt("IdNota");
        Titulo = this.getArguments().getString("Titulo");
        Accion = this.getArguments().getString("Accion");
        Descripcion = this.getArguments().getString("Descripcion");
        titulo = (EditText) view.findViewById(R.id.Titulo);
        descripcion = (EditText) view.findViewById(R.id.Descripcion);
        guardar = view.findViewById(R.id.GuardarNota);
        cancelar = view.findViewById(R.id.Cancelar);
        titulo.setText(Titulo);
        descripcion.setText(Descripcion);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ConexionHelper conn =  new ConexionHelper(getContext(),"bd_notas",null,1);
                    SQLiteDatabase db = conn.getWritableDatabase();
                    if(Accion.equals("Ins")){
                        db.execSQL("insert into notas (titulo,descripcion,fecha) values('"
                                + titulo.getText().toString() + "'," +
                                "'"+descripcion.getText().toString()+"'," +
                                "'"+FechaActual+"')");
                    }else{
                        db.execSQL("update notas set titulo = '"
                                + titulo.getText().toString() + "',descripcion = '"
                                + descripcion.getText().toString() + "' where id="
                                + IdNota + "");
                    }

                    Toast.makeText(getContext(),"Informacion Guardada Correctamente",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NotasFragment notasFragment = new NotasFragment();
                fragmentTransaction.replace(R.id.fragment,notasFragment);
                fragmentTransaction.commit();
            }
        });
    }


}
