package com.example.appnotas13sql.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.adapter.Adapter;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.model.Nota;
import com.example.appnotas13sql.ui.abas.LembretesFragment;
import com.example.appnotas13sql.ui.abas.NotasFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private Button buttonAbaNotas, buttonAbaLembretes;
    private FrameLayout frameConteudo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        buttonAbaNotas = view.findViewById(R.id.buttonAbaNotas);
        buttonAbaLembretes = view.findViewById(R.id.buttonAbaLembretes);
        frameConteudo = view.findViewById(R.id.frameConteudo);

        buttonAbaNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, new NotasFragment()).commit();
            }
        });

        buttonAbaLembretes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, new LembretesFragment()).commit();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, new NotasFragment()).commit();
    }
}