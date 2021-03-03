package com.example.appnotas13sql.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class EstatisticasFragment extends Fragment {

    private TextView textStatsTotalNotas, textStatsNotasAtivas,
            textStatsLembretes, textStatsArquivadas, textStatsLixeira;
    private ArmazenamentoBancoDeDados bancoDeDados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estatisticas, container, false);

        textStatsTotalNotas = view.findViewById(R.id.textStatsTotalNotas);
        textStatsNotasAtivas = view.findViewById(R.id.textStatsNotasAtivas);
        textStatsLembretes = view.findViewById(R.id.textStatsLembretes);
        textStatsArquivadas = view.findViewById(R.id.textStatsArquivadas);
        textStatsLixeira = view.findViewById(R.id.textStatsLixeira);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        int total = bancoDeDados.getQtdNotas(0) +
                bancoDeDados.getQtdNotas(1) +
                bancoDeDados.getQtdNotas(2);
        textStatsTotalNotas.setText(String.valueOf(total));
        textStatsNotasAtivas.setText(String.valueOf(getQtdNotasAtivas(0)));
        textStatsLembretes.setText(String.valueOf(getQtdNotasAtivas(1)));
        textStatsArquivadas.setText(String.valueOf(bancoDeDados.getQtdNotas(0)));
        textStatsLixeira.setText(String.valueOf(bancoDeDados.getQtdNotas(2)));
    }

    public int getQtdNotasAtivas(int isLembrete) {
        int qtd = 0;
        for (int i = 0; i < bancoDeDados.getQtdNotas(1); i++) {
            Nota nota = bancoDeDados.getNota(i, 1);
            if (nota.getLembrete() == isLembrete) {
                qtd ++;
            }
        }
        return qtd;
    }
}