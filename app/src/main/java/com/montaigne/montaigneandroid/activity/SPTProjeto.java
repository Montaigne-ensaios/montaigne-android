package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.adapter.SPTFuros;

public class SPTProjeto extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SPTFuros adapter;
    private String idProjeto;
    private ImageView imgHome, imgBack, imgNFuro;
    private Button btnCarimbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_projeto);

        idProjeto = getIntent().getStringExtra("idProjeto");

        setupButtons();

        adapter = new SPTFuros(this, idProjeto);
        recyclerView = findViewById(R.id.recyclerSPTProjeto);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.addEnsaio("Um ensaio muito louco", "id");
        adapter.addEnsaio("Um ensaio da pesada", "id2");
        adapter.addEnsaio("Ok está na hora de eu ir dormir mesmo", "id2");

    }

    private void setupButtons(){
        // configuração dos botões
        imgHome = findViewById(R.id.imgSPTProjetoHome);
        imgHome.setOnClickListener(v -> finish());  // todo: configurar Home adequadamente

        imgBack = findViewById(R.id.imgSPTProjetoBack);
        imgBack.setOnClickListener(v -> finish());

        imgNFuro = findViewById(R.id.imgSPTProjetoNFuro);
        imgNFuro.setOnClickListener(v -> {
                // intent para a activityde realização de ensaio
                Intent intent = new Intent(SPTProjeto.this, SPTCriar.class);
                intent.putExtra("idProjeto", idProjeto);
                intent.putExtra("idFuro", "criar");
                startActivity(intent);
            }
        );

        btnCarimbo = findViewById(R.id.btnSPTProjetoCarimbo);
        btnCarimbo.setOnClickListener(v -> {
            // intentn para a activity de carimbo
            Intent intent = new Intent(SPTProjeto.this, SPTCarimbo.class);
            intent.putExtra("idProjeto", idProjeto);
            startActivity(intent);
        });
    }
}