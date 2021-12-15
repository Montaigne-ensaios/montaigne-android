package com.montaigne.montaigneandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.adapter.SPTFuros;
import com.montaigne.montaigneandroid.dao.SondagemDAO;
import com.montaigne.montaigneandroid.model.Sondagem;

import java.util.List;

public class SPTProjeto extends AppCompatActivity {
    private long idProjeto;
    private List<Sondagem> sondagens;

    private RecyclerView recyclerView;
    private SPTFuros adapter;
    private ImageButton btnBack, btnAddFuro, btnRmFuro;
    private Button btnCarimbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_projeto);

        idProjeto = getIntent().getLongExtra("idProjeto", 0);

        SondagemDAO sondagemDAO = new SondagemDAO(getApplicationContext());
        sondagens = sondagemDAO.pesquisar(idProjeto);

        setupButtons();

        adapter = new SPTFuros(this, idProjeto, sondagens);
        recyclerView = findViewById(R.id.recyclerSPTProjeto);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupButtons(){
        // configuração dos botões
        btnBack = findViewById(R.id.btnSPTProjetoBackActivySpt);
        btnBack.setOnClickListener(v -> finish());

        btnAddFuro = findViewById(R.id.bntSptProjetoAddFuro);
        btnAddFuro.setOnClickListener(v -> {
                // intent para a activity de realização de ensaio
                Intent intent = new Intent(SPTProjeto.this, SPTCriar.class);
                intent.putExtra("idProjeto", idProjeto);
                intent.putExtra("idFuro", "criar");
                startActivity(intent);
            }
        );

        btnRmFuro = findViewById(R.id.bntSptProjetoExcluirFuro);
        btnRmFuro.setOnClickListener(v -> {});  // todo: implementar remoção de furos

        btnCarimbo = findViewById(R.id.btnSPTProjetoAlterarCarimbo);
        btnCarimbo.setOnClickListener(v -> {
            // intentn para a activity de carimbo
            Intent intent = new Intent(SPTProjeto.this, SPTCarimbo.class);
            intent.putExtra("idProjeto", idProjeto);
            startActivity(intent);
        });
    }
}