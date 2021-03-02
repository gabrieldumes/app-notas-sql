package com.example.appnotas13sql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.helper.ArmazenamentoPreferencias;
import com.example.appnotas13sql.model.Nota;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Nota> listaNotas;
    private Context context;
    private ArmazenamentoPreferencias preferencias;

    public Adapter(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_lista, parent, false);
        this.context = parent.getContext();
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        preferencias = new ArmazenamentoPreferencias(context);
        holder.textTitulo.setTextSize(preferencias.getTamanhoFonte("titulo"));
        holder.textNota.setTextSize(preferencias.getTamanhoFonte("texto"));
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(preferencias.getCorNota()));

        if (position == listaNotas.size() - 1) {
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
            layoutParams.setMargins(
                    dpToPixel(10),
                    dpToPixel(12),
                    dpToPixel(10),
                    dpToPixel(12)
            );
        } else {
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
            layoutParams.setMargins(
                    dpToPixel(10),
                    dpToPixel(12),
                    dpToPixel(10),
                    dpToPixel(0)
            );
        }

        Nota nota = listaNotas.get(position);
        holder.textTitulo.setText(nota.getTitulo());
        holder.textNota.setText(nota.getTexto());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitulo, textNota;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitulo = itemView.findViewById(R.id.textRecyclerTitulo);
            textNota = itemView.findViewById(R.id.textRecyclerNota);
            cardView = itemView.findViewById(R.id.cardViewItemLista);
        }
    }

    public int dpToPixel(int valorEmDp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (valorEmDp * density);
    }
}
