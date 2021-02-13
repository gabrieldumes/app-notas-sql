package com.example.appnotas13sql.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;
import com.example.appnotas13sql.model.Nota;

public class NovaNotaActivity extends AppCompatActivity {

    private EditText editTituloNota, editTextoNota;
    private ToggleButton toggleLembrete;
    private ArmazenamentoBancoDeDados bancoDeDados;

    private Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);

        editTituloNota = findViewById(R.id.editTituloNota);
        editTextoNota = findViewById(R.id.editTextoNota);
        toggleLembrete = findViewById(R.id.toggleLembrete);

        bancoDeDados = new ArmazenamentoBancoDeDados(this);

        try {
            Bundle bundle = getIntent().getExtras();
            this.nota = (Nota) bundle.getSerializable("nota-selecionada");
        } catch (Exception e) {
            this.nota = null;
            Log.i("INSETO", e.getMessage());
        }

        if (nota != null) {
            editTituloNota.setText(nota.getTitulo());
            editTextoNota.setText(nota.getTexto());
            if (nota.getLembrete() == 1) toggleLembrete.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String tituloNota = editTituloNota.getText().toString();
        String textoNota = editTextoNota.getText().toString();
        if (nota != null) {
            if (tituloNota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Campo Título VAZIO!", Toast.LENGTH_SHORT).show();
            } else if (textoNota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Campo Nota VAZIO!", Toast.LENGTH_SHORT).show();
            } else {
                int lembreteAtivado = 0;
                if (toggleLembrete.isChecked()) {
                    lembreteAtivado = 1;
                }
                bancoDeDados.updateNota(nota.getId(), tituloNota, textoNota, lembreteAtivado);
                Toast.makeText(getApplicationContext(), "Nota alterada com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            if (tituloNota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Campo Título VAZIO!", Toast.LENGTH_SHORT).show();
            } else if (textoNota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Campo Nota VAZIO!", Toast.LENGTH_SHORT).show();
            } else {
                int lembreteAtivado = 0;
                if (toggleLembrete.isChecked()) {
                    lembreteAtivado = 1;
                }
                bancoDeDados.addNota(tituloNota, textoNota, lembreteAtivado);
                Toast.makeText(getApplicationContext(), "Nota criada com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}