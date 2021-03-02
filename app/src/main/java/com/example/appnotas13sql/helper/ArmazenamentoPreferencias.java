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

    private final String CHAVE_TAMANHO_TITULO = "tamanho_titulo";
    private final String CHAVE_TAMANHO_TEXTO = "tamanho_texto";
    private final String CHAVE_SEEK_PROGRESS = "seek_progress";
    private final String CHAVE_COR_NOTA = "cor_nota";

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

    //------> CONFIGURAÇÕES <------

    public void setTamanhoFonte(float titulo, float texto, float progress) {
        editor.putFloat(CHAVE_TAMANHO_TITULO, titulo);
        editor.putFloat(CHAVE_TAMANHO_TEXTO, texto);
        editor.putFloat(CHAVE_SEEK_PROGRESS, progress);
        editor.commit();
    }

    public float getTamanhoFonte(String opcao) {
        switch (opcao) {
            case "titulo":
                return preferences.getFloat(CHAVE_TAMANHO_TITULO, 20);
            case "texto":
                return preferences.getFloat(CHAVE_TAMANHO_TEXTO, 17);
            case "progress":
                return preferences.getFloat(CHAVE_SEEK_PROGRESS, 1);
            default:
                return 5;
        }
    }

    public void setRadioIdCorNota(int radioId) {
        editor.putInt(CHAVE_COR_NOTA, radioId).commit();
    }

    public int getRadioIdCorNota() {
        return preferences.getInt(CHAVE_COR_NOTA, R.id.radioCorAmarelo);
    }

    public int getCorNota() {
        switch (preferences.getInt(CHAVE_COR_NOTA, R.id.radioCorAmarelo)) {
            case R.id.radioCorVerde:
                return R.color.cor_nota_verde;
            case R.id.radioCorCinza:
                return R.color.cor_nota_cinza;
            default:
                return R.color.cor_nota_amarelo;
        }
    }
}
