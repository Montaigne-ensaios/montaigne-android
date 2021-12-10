package com.montaigne.montaigneandroid.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.adapter.HomeProjs;
import com.montaigne.montaigneandroid.dao.ProjetoDAO;
import com.montaigne.montaigneandroid.model.Projeto;

import java.util.List;

public class SPTGerenciarProjs extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeProjs adapter;
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
        adapter = new HomeProjs(this, projetos);
        recyclerView = findViewById(R.id.recyclerSPTGerenciar);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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