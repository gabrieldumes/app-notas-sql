package com.example.appnotas13sql.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appnotas13sql.R;

public class ArmazenamentoPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String ARQUIVO_PREFERENCIAS = "app_preferencias";

    private final String CHAVE_ORDENACAO = "ordenacao";
    private final String CHAVE_PESQUISA = "pesquisa";
    private final String CHAVE_FILTRO_EM_NOTAS = "em_notas";
    private final String CHAVE_FILTRO_EM_LEMBRETES = "em_lembretes";

    public ArmazenamentoPreferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        editor = preferences.edit();
    }

    //------> FILTROS <------

    public void setFiltros(int ordenacao, String pesquisa, boolean emNotas, boolean emLembretes) {
        editor.putInt(CHAVE_ORDENACAO, ordenacao);
        editor.putString(CHAVE_PESQUISA, pesquisa);
        editor.putBoolean(CHAVE_FILTRO_EM_NOTAS, emNotas);
        editor.putBoolean(CHAVE_FILTRO_EM_LEMBRETES, emLembretes);
        editor.commit();
    }

    public int getOrdenacao() {
        return preferences.getInt(CHAVE_ORDENACAO, R.id.radioMaisRecentes);
    }

    public String getPesquisa() {
        return preferences.getString(CHAVE_PESQUISA, "").toLowerCase();
    }

    public boolean isFiltroEmNotas() {
        return preferences.getBoolean(CHAVE_FILTRO_EM_NOTAS, false);
    }

    public boolean isFiltroEmLembretes() {
        return preferences.getBoolean(CHAVE_FILTRO_EM_LEMBRETES, false);
    }
}
