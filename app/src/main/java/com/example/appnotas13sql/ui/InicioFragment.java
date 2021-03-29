package com.example.appnotas13sql.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.activity.FiltrosActivity;
import com.example.appnotas13sql.adapter.Adapter;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.helper.ArmazenamentoPreferencias;
import com.example.appnotas13sql.model.Nota;
import com.example.appnotas13sql.ui.abas.LembretesFragment;
import com.example.appnotas13sql.ui.abas.NotasFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private TextView textPesquisa;
    private ArmazenamentoPreferencias preferencias;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        textPesquisa = view.findViewById(R.id.textPesquisa);

        preferencias = new ArmazenamentoPreferencias(getActivity());

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(),
                FragmentPagerItems.with(getActivity())
                .add("Notas", NotasFragment.class)
                .add("Lembretes", LembretesFragment.class)
                .create());
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout smartTabLayout = view.findViewById(R.id.smartTabLayout);
        smartTabLayout.setViewPager(viewPager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        textPesquisa.setVisibility(View.GONE);
        if (!preferencias.getPesquisa().equals("")) {
            textPesquisa.setVisibility(View.VISIBLE);
            textPesquisa.setText("Pesquisa: " + preferencias.getPesquisa());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //Toast.makeText(getActivity(), "onPause", Toast.LENGTH_SHORT).show();
        onDetach();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.filtrar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_filtrar) {
            startActivity(new Intent(getActivity(), FiltrosActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}