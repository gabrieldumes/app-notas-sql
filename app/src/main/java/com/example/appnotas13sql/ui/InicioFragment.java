package com.example.appnotas13sql.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    //private SmartTabLayout smartTabLayout;
    //private ViewPager viewPager;

    private RecyclerView recyclerViewInicioTESTE;
    private List<Nota> listaNotas = new ArrayList<>();
    private ArmazenamentoBancoDeDados bancoDeDados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerViewInicioTESTE = view.findViewById(R.id.recyclerViewInicioTESTE);

        bancoDeDados = new ArmazenamentoBancoDeDados(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewInicioTESTE.setLayoutManager(layoutManager);

        /*smartTabLayout = view.findViewById(R.id.smartTabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(),
                FragmentPagerItems.with(getActivity())
                    .add("Notas", NotasFragment.class)
                    .add("Lembretes", LembretesFragment.class)
                .create()

        );

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);*/

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listaNotas.clear();
        popularLista();
        Adapter adapter = new Adapter(listaNotas);
        recyclerViewInicioTESTE.setAdapter(adapter);
    }

    public void popularLista(){
        for (int i = 0; i < bancoDeDados.getQtdNotas(1); i++) {
            Nota nota = bancoDeDados.getNota(i, 1);
            listaNotas.add(nota);
        }
    }
}