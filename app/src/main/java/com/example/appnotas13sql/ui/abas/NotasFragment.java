package com.example.appnotas13sql.ui.abas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.model.Nota;

public class NotasFragment extends Fragment {

    private TextView textTesteTitulo, textTesteTexto, textTesteId;
    private ArmazenamentoBancoDeDados bancoDeDados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notas, container, false);

        textTesteTitulo = view.findViewById(R.id.textTesteTitulo);
        textTesteTexto = view.findViewById(R.id.textTesteTexto);
        textTesteId = view.findViewById(R.id.textTesteId);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Nota nota = bancoDeDados.getNota(0, 1);
        textTesteTitulo.setText(nota.getTitulo());
        textTesteTexto.setText(nota.getTexto());
        textTesteId.setText(String.valueOf(nota.getId()));
    }
}