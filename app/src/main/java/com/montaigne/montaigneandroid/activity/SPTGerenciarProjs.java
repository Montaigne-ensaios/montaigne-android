package com.montaigne.montaigneandroid.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.adapter.SPTGerenciar;
import com.montaigne.montaigneandroid.dao.ProjetoDAO;
import com.montaigne.montaigneandroid.model.Projeto;

import java.util.List;

public class SPTGerenciarProjs extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SPTGerenciar adapter;
    private ImageView imgCriar, imgVoltar;
    private List<Projeto> projetos;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_gerenciar_projs);

        setupButtons();

        ProjetoDAO projetoDAO = new ProjetoDAO(getApplicationContext());
        projetos = projetoDAO.listar();

        // cria o adaptador e seta o recycler
        adapter = new SPTGerenciar(this, projetos);
        recyclerView = findViewById(R.id.recyclerSPTGerenciar);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // adiciona elementos ao recycler
        // todo: ler ensaios e ids da base de dados e adicionar automaticamente (definir descrição)
        adapter.addEnsaio("Exemplo de ensaio que não sei qual por",
                "Aqui devo inserir uma longa descrição, embora não tenho criatividade " +
                        "e isso tudo vai ser descartado", "todo: definir ids");

        adapter.addEnsaio("Outro exemplo aqui",
                "Bom dia! Tudo bem com você? Espero que esteja gostando das alterações que " +
                        "no projeto :)", "id id id cadê você?");
    }

    private void setupButtons(){
        // configura botão de criar novo projeto indo para activity de carimbo,
        // mas não fecha esta activity
        imgCriar = findViewById(R.id.imgSPTGerenciarCriar);
        imgCriar.setOnClickListener(v -> {
            Intent intent = new Intent(SPTGerenciarProjs.this, SPTCarimbo.class);
            intent.putExtra("idProjeto", "criar");
            startActivity(intent);
        });

        // configura botão de voltar
        imgVoltar = findViewById(R.id.imgSPTGerenciarBack);
        imgVoltar.setOnClickListener(v -> finish());
    }
}