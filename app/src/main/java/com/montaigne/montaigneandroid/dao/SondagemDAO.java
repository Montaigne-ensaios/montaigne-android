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
import com.montaigne.montaigneandroid.model.Sondagem;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SondagemDAO {
    private SQLiteDatabase writer;
    private SQLiteDatabase reader;

    public SondagemDAO(Context context) {
        DbHelper db = new DbHelper( context );
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean salvar(Sondagem sondagem) {
        ContentValues cv = new ContentValues();
        cv.put("id_spt", sondagem.getIdSpt());
        cv.put("numero", sondagem.getNumero());
        cv.put("nivel_dagua", sondagem.getNivelDAgua());
        cv.put("nivel_furo", sondagem.getNivelFuro());
        cv.put("nivel_referencia", sondagem.getNivelReferencia());
        cv.put("total_perfurado", sondagem.getTotalPerfurado());
        cv.put("coordenada", sondagem.getCoordenada());
        cv.put("data_inicio", sondagem.getDataInicio().getTime());

        try {
            // Não registra na tabela se não der certo
            long num = writer.insert(DbHelper.TABELA_SONDAGEM_SPT, null, cv);

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
            writer.delete(DbHelper.TABELA_SONDAGEM_SPT, "id = ?", args);
            Log.i("INFO", "Sucesso ao excluir dado na tabela");

        } catch (Exception e) {
            Log.e("INFO", "Erro ao excluir dado na tabela: " + e.getMessage());
            return false;
        }

        return true;
    }

    @SuppressLint("Range")
    public ArrayList<Long> pesquisar(long idSpt) {
        ArrayList<Long> ids = new ArrayList<>();

        // Comando
        String sql = "SELECT id FROM " + DbHelper.TABELA_SONDAGEM_SPT + " WHERE id_spt == " + idSpt + ";";

        // Execução
        Cursor c = reader.rawQuery(sql, null);


        while ( c.moveToNext() ) {
            // Cada dado tem seu método get lá do objeto c
            ids.add(c.getLong(c.getColumnIndex("id")));

        }

        return ids;
    }


    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Sondagem> listar() {
        List<Sondagem> sondagens = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_SONDAGEM_SPT + " ;";
        Cursor c = reader.rawQuery(sql, null);

        while ( c.moveToNext() ) {
            Long id = c.getLong( c.getColumnIndex("id")  );
            Long idSpt = c.getLong( c.getColumnIndex("id_spt")  );
            int numero = c.getInt( c.getColumnIndex("numero") );
            float nivelDAgua = c.getFloat( c.getColumnIndex("nivel_dagua") );
            float nivelFuro = c.getFloat( c.getColumnIndex("nivel_furo") );
            float nivelReferencia = c.getFloat( c.getColumnIndex("nivel_referencia") );
            float totalPerfurado = c.getFloat( c.getColumnIndex("total_perfurado") );
            String coordenada = c.getString( c.getColumnIndex("coordenada") );
            Long data = c.getLong( c.getColumnIndex("data_inicio") );
            Date dataInicio = Date.from(Instant.ofEpochMilli(data));

            Sondagem sondagem = new Sondagem(id, idSpt, numero, nivelDAgua, nivelFuro, nivelReferencia,
                                            totalPerfurado, coordenada, dataInicio);
            sondagens.add(sondagem);
        }

        return sondagens;
    }
}
