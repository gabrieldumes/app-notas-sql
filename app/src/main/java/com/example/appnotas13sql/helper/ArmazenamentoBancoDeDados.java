package com.example.appnotas13sql.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appnotas13sql.model.Nota;

public class ArmazenamentoBancoDeDados {

    private Context context;
    private SQLiteDatabase database;

    public ArmazenamentoBancoDeDados(Context context) {
        this.context = context;
        try {
            database = context.openOrCreateDatabase("app_db", Context.MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS notas " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, " +
                    "texto VARCHAR, status INT(1), lembrete INT(1))");

            //status = 0: nota arquivada / status = 1: nota ativa / status = 2: excluida
            //lembrete = 0: lembrete desativado / lembrete = 1: lembrete ativado

        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
        }
    }

    public void addNota(String tituloNota, String textoNota, int lembrete) {
        try {
            database.execSQL("INSERT INTO notas(titulo, texto, status, lembrete) VALUES " +
                    "('" + tituloNota + "', '" + textoNota + "', 1, " + lembrete + ")");
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
        }
    }

    public void updateNota(int id, String tituloNota, String textoNota, int lembrete) {
        try {
            database.execSQL("UPDATE notas SET titulo='" + tituloNota + "', texto='" + textoNota
                    + "', lembrete=" + lembrete + " WHERE id=" + id);
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
        }
    }

    public void updateStatusNota(int id, int status) {
        try {
            database.execSQL("UPDATE notas SET status = " + status + " WHERE id=" + id);
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
        }
    }

    public Nota getNota(int position, int status) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM notas WHERE status = " + status, null);
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTitulo = cursor.getColumnIndex("titulo");
            int indiceColunaTexto = cursor.getColumnIndex("texto");
            int indiceColunaStatus = cursor.getColumnIndex("status");
            int indiceColunaLembrete = cursor.getColumnIndex("lembrete");
            cursor.moveToPosition(position);
            return new Nota(
                    cursor.getInt(indiceColunaId),
                    cursor.getString(indiceColunaTitulo),
                    cursor.getString(indiceColunaTexto),
                    cursor.getInt(indiceColunaStatus),
                    cursor.getInt(indiceColunaLembrete)
            );
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
            return new Nota(1111, "Erro", "Erro", 1111, 1111);
        }
    }

    public int getQtdNotas(int status) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM notas WHERE status = " + status, null);
            return cursor.getCount();
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
            return 0;
        }
    }

    public void deleteNota(int id) {
        try {
            database.execSQL("DELETE FROM notas WHERE id = " + id);
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
        }
    }

    public void excluirTodosOsDados() {
        try {
            database.execSQL("DROP TABLE notas");
        } catch (Exception e) {
            Log.i("INSETO", e.getMessage());
        }
    }
}
