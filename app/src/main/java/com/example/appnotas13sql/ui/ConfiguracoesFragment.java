package com.example.appnotas13sql.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appnotas13sql.R;

public class ConfiguracoesFragment extends Fragment {

    private SeekBar seekTamanhoFonte;
    private RadioGroup radioGroupCorFundoNotas;
    private RadioButton radioCorAmarelo, radioCorVerde, radioCorCinza;
    private Button buttonExportarNotas;
    private TextView textConfigResetarFiltros, textDeletarDados, textRestaurarConfiguracoes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);

        seekTamanhoFonte = view.findViewById(R.id.seekTamanhoFonte);
        radioGroupCorFundoNotas = view.findViewById(R.id.radioGroupCorFundoNotas);
        radioCorAmarelo = view.findViewById(R.id.radioCorAmarelo);
        radioCorVerde = view.findViewById(R.id.radioCorVerde);
        radioCorCinza = view.findViewById(R.id.radioCorCinza);
        buttonExportarNotas = view.findViewById(R.id.buttonExportarNotas);
        textConfigResetarFiltros = view.findViewById(R.id.textConfigResetarFiltros);
        textDeletarDados = view.findViewById(R.id.textDeletarDados);
        textRestaurarConfiguracoes = view.findViewById(R.id.textRestaurarConfiguracoes);

        return view;
    }
}