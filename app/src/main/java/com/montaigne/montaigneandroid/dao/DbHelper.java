package com.montaigne.montaigneandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "db_montaigne";
    public static String TABELA_PROJETO_SPT = "spt";
    public static String TABELA_SONDAGEM_SPT = "sondagem";
    public static String TABELA_AMOSTRA_SPT = "amostra";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Fazer teste unitário

        // TABELAS DE PROJETOS SPT
        criarTabelaProjetoSPT(db);
        criarTabelaSondagemSPT(db);
        criarTabelaAmostraSPT(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Executado se no telefone já tivesse um banco desse app, normalmente ao atualizar versão

        // Deleta as tabelas existentes
        String dropProjetoSPT = "DROP TABLE IF EXISTS " + TABELA_PROJETO_SPT + " ; ";
        String dropSondagemSPT = "DROP TABLE IF EXISTS " + TABELA_SONDAGEM_SPT + " ; ";
        String dropAmostraSPT = "DROP TABLE IF EXISTS " + TABELA_AMOSTRA_SPT + " ; ";

        try {
            db.execSQL( dropProjetoSPT );
            db.execSQL( dropSondagemSPT );
            db.execSQL( dropAmostraSPT );

            onCreate( db );
            Log.i("INFO DB", "Sucesso ao atualizar app");

        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao atualizar app" + e.getStackTrace());
        }
    }

    private void criarTabelaProjetoSPT(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE IF NOT EXISTS " + TABELA_PROJETO_SPT + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT NOT NULL UNIQUE," +
                        "cliente TEXT," +
                        "empresa TEXT," +
                        "telefone TEXT," +
                        "tecnico_responsavel TEXT," +
                        "endereco TEXT," +
                        "numero_furos INTEGER," +
                        "data_inicio TEXT" +
                        ");";

        criarTabelaSQLite(db, TABELA_PROJETO_SPT, sql);
    }

    private void criarTabelaSondagemSPT(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE IF NOT EXISTS " + TABELA_SONDAGEM_SPT + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "id_spt INTEGER NOT NULL," +
                        "numero INTEGER," +
                        "nivel_dagua DECIMAL(5, 2)," +
                        "nivel_furo DECIMAL(5, 2)," +
                        "nivel_referencia DECIMAL(5, 2)," +
                        "total_perfurado DECIMAL(5, 2)," +
                        "coordenada TEXT," +
                        "data_inicio TEXT," +

                        "FOREIGN KEY(id_spt) references spt(id)" +
                        ");";

        criarTabelaSQLite(db, TABELA_SONDAGEM_SPT, sql);
    }

    private void criarTabelaAmostraSPT(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE IF NOT EXISTS " + TABELA_AMOSTRA_SPT + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "id_sondagem INTEGER NOT NULL," +
                        "golpes1 INTEGER NOT NULL," +
                        "golpes2 INTEGER," +
                        "golpes3 INTEGER," +
                        "nspt INTEGER NOT NULL," +

                        "FOREIGN KEY(id_sondagem) references sondagem(id)" +
                        ");";

        criarTabelaSQLite(db, TABELA_AMOSTRA_SPT, sql);
    }

    private void criarTabelaSQLite(SQLiteDatabase db, String nome, String sql) {
        try {
            db.execSQL( sql );
            Log.i("INFO DB", "Sucesso ao criar tabela " + nome);
        } catch (Exception e) {
            Log.e("INFO DB", "Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
