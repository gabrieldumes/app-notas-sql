package com.example.appnotas13sql.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnotas13sql.R;
import com.example.appnotas13sql.helper.ArmazenamentoBancoDeDados;

public class NovaNotaActivity extends AppCompatActivity {

    private EditText editTituloNota, editTextoNota;
    private ArmazenamentoBancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);

        editTituloNota = findViewById(R.id.editTituloNota);
        editTextoNota = findViewById(R.id.editTextoNota);

        bancoDeDados = new ArmazenamentoBancoDeDados(this);
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
        if (tituloNota.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Campo Título VAZIO!", Toast.LENGTH_SHORT).show();
        } else if (textoNota.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Campo Nota VAZIO!", Toast.LENGTH_SHORT).show();
        } else {
            bancoDeDados.addNota(tituloNota, textoNota,0);
            Toast.makeText(getApplicationContext(), "Nota salva com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}