package com.example.appnotas13sql.ui.abas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.activity.NovaNotaActivity;
import com.example.appnotas13sql.adapter.Adapter;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.helper.ArmazenamentoPreferencias;
import com.example.appnotas13sql.helper.RecyclerItemClickListener;
import com.example.appnotas13sql.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class LembretesFragment extends Fragment {

    private RecyclerView recyclerViewLembretes;
    private List<Nota> listaNotas = new ArrayList<>();
    private ArmazenamentoBancoDeDados bancoDeDados;
    private ArmazenamentoPreferencias preferencias;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lembretes, container, false);

        recyclerViewLembretes = view.findViewById(R.id.recyclerViewLembretes);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());
        preferencias = new ArmazenamentoPreferencias(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewLembretes.setLayoutManager(layoutManager);

        recyclerViewLembretes.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerViewLembretes,
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
                                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                                dialog.setTitle("Quer excluir o lembrete?");
                                dialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Nota nota = listaNotas.get(position);
                                        bancoDeDados.updateStatusNota(nota.getId(), 2);
                                        onStart();
                                    }
                                });
                                dialog.setNegativeButton("Cancelar", null);
                                dialog.create().show();
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
        recyclerViewLembretes.setAdapter(adapter);
    }

    public void popularLista() {
        if (preferencias.isFiltroEmLembretes()) {
            String pesquisa = preferencias.getPesquisa();
            switch (preferencias.getOrdenacao()) {
                case R.id.radioMaisRecentes:
                    for (int i = bancoDeDados.getQtdNotas(1); i > 0; i--) {
                        Nota nota = bancoDeDados.getNota(i - 1, 1);
                        if (!pesquisa.equals("")) {
                            if (nota.getTexto().toLowerCase().contains(pesquisa) ||
                                    nota.getTitulo().toLowerCase().contains(pesquisa)) {
                                if (nota.getLembrete() == 1) listaNotas.add(nota);
                            }
                        } else {
                            if (nota.getLembrete() == 1) listaNotas.add(nota);
                        }
                    }
                    break;
                case R.id.radioMaisAntigas:
                    for (int i = 0; i < bancoDeDados.getQtdNotas(1); i++) {
                        Nota nota = bancoDeDados.getNota(i, 1);
                        if (!pesquisa.equals("")) {
                            if (nota.getTexto().toLowerCase().contains(pesquisa) ||
                                    nota.getTitulo().toLowerCase().contains(pesquisa)) {
                                if (nota.getLembrete() == 1) listaNotas.add(nota);
                            }
                        } else {
                            if (nota.getLembrete() == 1) listaNotas.add(nota);
                        }
                    }
            }
        } else {
            //dessa forma o primeiro item da lista será a nota mais RECENTE
            for (int i = bancoDeDados.getQtdNotas(1); i > 0; i--) {
                Nota nota = bancoDeDados.getNota(i - 1, 1);
                if (nota.getLembrete() == 1) listaNotas.add(nota);
            }
        }
    }
}