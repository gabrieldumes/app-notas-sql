package com.example.appnotas13sql.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.appnotas13sql.activity.FiltrosActivity;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.helper.ArmazenamentoPreferencias;

public class ConfiguracoesFragment extends Fragment {

    private SeekBar seekTamanhoFonte;
    private RadioGroup radioGroupCorFundoNotas;
    private Button buttonExportarNotas;
    private TextView textConfigResetarFiltros, textDeletarDados, textRestaurarConfiguracoes;

    private ArmazenamentoPreferencias preferencias;
    private ArmazenamentoBancoDeDados bancoDeDados;

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
        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        buttonExportarNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Funcionalidade indisponível", Toast.LENGTH_SHORT).show();
            }
        });

        textConfigResetarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.setFiltros(R.id.radioMaisRecentes, "", false, false);
                Toast.makeText(getActivity(), "Filtros resetados", Toast.LENGTH_SHORT).show();
            }
        });

        textDeletarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Quer excluir todos os dados permanentemente?");
                dialog.setMessage("Esta ação não pode ser desfeita");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Exluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //bancoDeDados.excluirTodosOsDados();
                        Toast.makeText(getActivity(), "Dados excluídos", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancelar", null);
                dialog.create().show();
            }
        });

        textRestaurarConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.setTamanhoFonte(20, 17, 1);
                preferencias.setRadioIdCorNota(R.id.radioCorAmarelo);
                onStart();
                Toast.makeText(getActivity(), "Configurações restauradas", Toast.LENGTH_SHORT).show();
            }
        });

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