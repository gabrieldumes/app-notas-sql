package com.example.appnotas13sql.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnotas13sql.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class SobreFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String descricao = "O app Notas 1.3 permite criar, editar ou remover notas " +
                "e lembretes de forma simples. Algumas outras funcionalidadas são: " +
                "Arquivar e restaurar notas; Aplicar filtros e buscas; Ver estatísticas; " +
                "Alterar configurações do app";

        Element versao = new Element();
        versao.setTitle("Versão 1.3");

        View view = new AboutPage(getActivity())
                .setDescription(descricao)

                .addGroup("Entre em contato")
                .addEmail("gabriel@notas1.3.com", "Envie um e-mail")
                .addWebsite("https://github.com/gabrieldumes/app-notas-sql", "Acesse o site")

                .addGroup("Redes sociais")
                .addGitHub("gabrieldumes", "GitHub")
                .addFacebook("notas1.3", "Facebook")
                .addItem(versao)
                .create();
        return view;
    }
}