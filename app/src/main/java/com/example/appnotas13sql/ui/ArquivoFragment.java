package com.example.appnotas13sql.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.adapter.Adapter;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class ArquivoFragment extends Fragment {

    private RecyclerView recyclerViewArquivo;
    private List<Nota> listaNotas = new ArrayList<>();
    private ArmazenamentoBancoDeDados bancoDeDados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arquivo, container, false);

        recyclerViewArquivo = view.findViewById(R.id.recyclerViewArquivo);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewArquivo.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listaNotas.clear();
        popularLista();
        Adapter adapter = new Adapter(listaNotas);
        recyclerViewArquivo.setAdapter(adapter);
    }

    public void popularLista() {
        for (int i = 0; i < bancoDeDados.getQtdNotas(0); i++) {
            Nota nota = bancoDeDados.getNota(i, 0);
            listaNotas.add(nota);
        }
    }
}