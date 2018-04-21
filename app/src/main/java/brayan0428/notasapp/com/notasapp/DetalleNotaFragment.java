package brayan0428.notasapp.com.notasapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleNotaFragment extends Fragment{
    int IdNota = 0;
    String Titulo;
    String Descripcion;
    EditText titulo,descripcion;
    Button guardar,cancelar;
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
        Descripcion = this.getArguments().getString("Descripcion");
        titulo = (EditText) view.findViewById(R.id.Titulo);
        descripcion = (EditText) view.findViewById(R.id.Descripcion);
        titulo.setText(Titulo);
        descripcion.setText(Descripcion);
    }
}
