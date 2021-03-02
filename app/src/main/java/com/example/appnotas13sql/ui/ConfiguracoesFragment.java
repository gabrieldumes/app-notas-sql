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
import android.widget.Toast;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.helper.ArmazenamentoPreferencias;

public class ConfiguracoesFragment extends Fragment {

    private SeekBar seekTamanhoFonte;
    private RadioGroup radioGroupCorFundoNotas;
    private Button buttonExportarNotas;
    private TextView textConfigResetarFiltros, textDeletarDados, textRestaurarConfiguracoes;

    private ArmazenamentoPreferencias preferencias;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);

        seekTamanhoFonte = view.findViewById(R.id.seekTamanhoFonte);
        radioGroupCorFundoNotas = view.findViewById(R.id.radioGroupCorFundoNotas);
        buttonExportarNotas = view.findViewById(R.id.buttonExportarNotas);
        textConfigResetarFiltros = view.findViewById(R.id.textConfigResetarFiltros);
        textDeletarDados = view.findViewById(R.id.textDeletarDados);
        textRestaurarConfiguracoes = view.findViewById(R.id.textRestaurarConfiguracoes);

        preferencias = new ArmazenamentoPreferencias(getActivity());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        seekTamanhoFonte.setProgress((int) preferencias.getTamanhoFonte("progress"));
        radioGroupCorFundoNotas.check(preferencias.getRadioIdCorNota());
    }

    @Override
    public void onPause() {
        super.onPause();
        switch (seekTamanhoFonte.getProgress()) {
            case 0:
                preferencias.setTamanhoFonte(16, 13, 0);
                break;
            case 1:
                preferencias.setTamanhoFonte(20, 17, 1);
                break;
            case 2:
                preferencias.setTamanhoFonte(24, 20, 2);
                break;
        }
        preferencias.setRadioIdCorNota(radioGroupCorFundoNotas.getCheckedRadioButtonId());
    }
}