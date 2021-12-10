package com.montaigne.montaigneandroid.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.adapter.HomeCategorias;
import com.montaigne.montaigneandroid.adapter.HomeProjs;
import com.montaigne.montaigneandroid.dao.ProjetoDAO;
import com.montaigne.montaigneandroid.model.Projeto;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerCategorias, recyclerProjs;
    private HomeCategorias adapterCategorias;
    private HomeProjs adapterProjs;
    private List<Projeto> projetos;
    private ImageButton btnRemover;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // pega o projeto do banco de dados
        ProjetoDAO projetoDAO = new ProjetoDAO(getApplicationContext());
        projetos = projetoDAO.listar();

        // Recycler HomeCategorias: criar novo projeto de acordo com as categorias
        setupCategorias();
        // Recycler HomeProjs: acessar e editar projetos salvos
        setupProjs();

        btnRemover = findViewById(R.id.BntHomeExcluirProjeto);
        btnRemover.setOnClickListener(v -> {});
        // todo: configurar listener de remover projetos (issue #41)
    }

    private void setupCategorias(){
        // cria o adapter e seta o recycler:
        adapterCategorias = new HomeCategorias(this);
        recyclerCategorias = findViewById(R.id.recyclerCategorias);
        recyclerCategorias.setAdapter(adapterCategorias);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        // adiciona elementos ao recycler (isso talvez seja movido para outro lugar):
        adapterCategorias.addCategoria("SPT - Ensaio de reconhecimento simples", SPTCarimbo.class);
        adapterCategorias.addCategoria("Proctor Normal - Ensaio de compactação", SPTCarimbo.class);
        // todo: passar string pros resources
    }

    private void setupProjs(){
        // cria o adapter e seta o recycler
        adapterProjs = new HomeProjs(this, projetos);
        recyclerProjs = findViewById(R.id.recyclerProjs);
        recyclerProjs.setAdapter(adapterProjs);
        recyclerProjs.setLayoutManager(new LinearLayoutManager(this));
    }
}