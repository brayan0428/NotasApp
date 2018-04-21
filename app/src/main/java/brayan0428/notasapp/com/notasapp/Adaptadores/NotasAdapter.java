package brayan0428.notasapp.com.notasapp.Adaptadores;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import brayan0428.notasapp.com.notasapp.Clases.Notas;
import brayan0428.notasapp.com.notasapp.DetalleNotaFragment;
import brayan0428.notasapp.com.notasapp.R;
public class NotasAdapter extends  RecyclerView.Adapter<NotasAdapter.ViewHolder> {
    Context context;
    List<Notas> notas;

    public NotasAdapter(Context context,List<Notas> notas){
        this.context = context;
        this.notas = notas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notaitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.titulo.setText(notas.get(position).getTitulo());
        holder.fecha.setText(notas.get(position).getFecha().toString());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("IdNota",notas.get(position).getId());
                bundle.putString("Titulo",notas.get(position).getTitulo());
                bundle.putString("Descripcion",notas.get(position).getDescripcion());
                bundle.putString("Accion","Act");
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DetalleNotaFragment detalleNotaFragment = new DetalleNotaFragment();
                detalleNotaFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment,detalleNotaFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo,fecha;
        LinearLayout item;
        public ViewHolder(final View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            titulo = itemView.findViewById(R.id.titulo);
            fecha = itemView.findViewById(R.id.fecha);
            item = itemView.findViewById(R.id.item);
        }
    }
}