package com.example.appnotas13sql.ui.abas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.activity.NovaNotaActivity;
import com.example.appnotas13sql.adapter.Adapter;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.helper.RecyclerItemClickListener;
import com.example.appnotas13sql.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotasFragment extends Fragment {

    private RecyclerView recyclerViewNotas;
    private List<Nota> listaNotas = new ArrayList<>();
    private ArmazenamentoBancoDeDados bancoDeDados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notas, container, false);

        recyclerViewNotas = view.findViewById(R.id.recyclerViewNotas);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewNotas.setLayoutManager(layoutManager);

        recyclerViewNotas.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerViewNotas,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Nota nota = listaNotas.get(position);
                                Intent intent = new Intent(getActivity(), NovaNotaActivity.class);
                                intent.putExtra("nota-selecionada", nota);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listaNotas.clear();
        popularLista();
        Adapter adapter = new Adapter(listaNotas);
        recyclerViewNotas.setAdapter(adapter);
    }

    public void popularLista() {
        //dessa forma o primeiro item da lista será a nota mais antiga
        for (int i = 0; i < bancoDeDados.getQtdNotas(1); i++) {
            Nota nota = bancoDeDados.getNota(i, 1);
            if (nota.getLembrete() == 0) listaNotas.add(nota);
        }
    }
}