package com.montaigne.montaigneandroid.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.montaigne.montaigneandroid.model.Projeto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ProjetoDAO {
    private SQLiteDatabase writer;
    private SQLiteDatabase reader;

    public ProjetoDAO(Context context) {
        DbHelper db = new DbHelper( context );
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean salvar(Projeto projeto) {
        ContentValues cv = new ContentValues();
        cv.put("nome", projeto.getNome());
        cv.put("endereco", projeto.getEndereco());
        cv.put("telefone", projeto.getTelefone());
        cv.put("tecnico", projeto.getTecnico());
        cv.put("responsavel", projeto.getResponsavel());
        cv.put("data_inicio", projeto.getDataInicio().atZone(ZoneId.systemDefault())
                                                        .toInstant().toEpochMilli());

        try {
            long num = writer.insert(DbHelper.TABELA_PROJETO_SPT, null, cv);

            if(num != -1) {
                Log.i("INFO", "Sucesso ao salvar dado na tabela, cod: " + num);
            } else {
                throw new Exception("entrada invalida ou tabela repetida.");
            }

        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar dado na tabela: " + e.getMessage());
            return false;
        }

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean atualizar(Projeto projeto) {
        // Dados para inserção na tabela

        try {
            // Código para atualizar tabela
            Log.i("INFO", "Sucesso ao atualizar dado na tabela");

        } catch (Exception e) {
            Log.e("INFO", "Erro ao atualizar dado na tabela: " + e.getMessage());
            return false;
        }

        return true;
    }


    public boolean deletar(Projeto projeto) {
        try {
            String[] args = {projeto.getId().toString()};
            writer.delete(DbHelper.TABELA_PROJETO_SPT, "id = ?", args);
            Log.i("INFO", "Sucesso ao excluir dado na tabela");

        } catch (Exception e) {
            Log.e("INFO", "Erro ao excluir dado na tabela: " + e.getMessage());
            return false;
        }

        return true;
    }

    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Projeto> listar() {
        // Método que obtem os dados

        List<Projeto> projetos = new ArrayList<>();

        // Comando
        String sql = "SELECT * FROM " + DbHelper.TABELA_PROJETO_SPT + " ;";

        // Execução
        Cursor c = reader.rawQuery(sql, null);

        // Captura os dados do comando(cursor)
        while ( c.moveToNext() ){
            // Cada dado tem seu método get lá do objeto c
            Long id = c.getLong( c.getColumnIndex("id")  );
            String nome = c.getString( c.getColumnIndex("nome") );
            String endereco = c.getString( c.getColumnIndex("endereco") );
            String telefone = c.getString( c.getColumnIndex("telefone") );
            String tecnico = c.getString( c.getColumnIndex("tecnico") );
            String responsavel = c.getString( c.getColumnIndex("responsavel") );
            Long data = c.getLong( c.getColumnIndex("data_inicio") );
            LocalDateTime dataInicio = LocalDateTime.ofInstant(Instant.ofEpochMilli(data), TimeZone.getDefault().toZoneId());

            Projeto projeto = new Projeto(id, nome, endereco, telefone, tecnico, responsavel, dataInicio);

            projetos.add(projeto);
        }

        return projetos;
    }
}
