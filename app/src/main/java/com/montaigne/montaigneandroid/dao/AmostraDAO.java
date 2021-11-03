package com.montaigne.montaigneandroid.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.montaigne.montaigneandroid.model.Amostra;
import com.montaigne.montaigneandroid.model.Projeto;

import java.util.ArrayList;
import java.util.List;

public class AmostraDAO {
    private SQLiteDatabase writer;
    private SQLiteDatabase reader;

    public AmostraDAO(Context context) {
        DbHelper db = new DbHelper( context );
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean salvar(Amostra amostra) {
        ContentValues cv = new ContentValues();
        cv.put("id_sondagem", amostra.getIdSondagem());
        cv.put("golpes1", amostra.getGolpes1());
        cv.put("golpes2", amostra.getGolpes2());
        cv.put("golpes3", amostra.getGolpes3());
        cv.put("nspt", amostra.getNspt());

        try {
            // Não registra na tabela se não der certo
            long num = writer.insert(DbHelper.TABELA_AMOSTRA_SPT, null, cv);

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
            writer.delete(DbHelper.TABELA_AMOSTRA_SPT, "id = ?", args);
            Log.i("INFO", "Sucesso ao excluir dado na tabela");

        } catch (Exception e) {
            Log.e("INFO", "Erro ao excluir dado na tabela: " + e.getMessage());
            return false;
        }

        return true;
    }

    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Amostra> listar(long idSondagem) {
        List<Amostra> amostras = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_AMOSTRA_SPT + " WHERE id_sondagem = ?;";
        Cursor c = reader.rawQuery(sql, new String[]{ String.valueOf(idSondagem) });

        while ( c.moveToNext() ){
            Long id = c.getLong( c.getColumnIndex("id")  );
            int golpes1 = c.getInt( c.getColumnIndex("golpes1") );
            int golpes2 = c.getInt( c.getColumnIndex("golpes2") );
            int golpes3 = c.getInt( c.getColumnIndex("golpes3") );
            int nspt = c.getInt( c.getColumnIndex("nspt") );

            Amostra amostra = new Amostra(id, idSondagem, golpes1, golpes2, golpes3, nspt);

            amostras.add(amostra);
        }

        return amostras;
    }
}
