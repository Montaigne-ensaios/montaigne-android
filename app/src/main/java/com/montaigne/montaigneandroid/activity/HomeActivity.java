package com.montaigne.montaigneandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.adapter.HomeCategorias;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeCategorias adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // cria o adapter e seta o recycler:
        adapter = new HomeCategorias(this);
        recyclerView = findViewById(R.id.recyclerCategorias);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        // adiciona elementos ao recycler (isso talvez seja movido para outro lugar):
        adapter.addCategoria("SPT - Ensaio de reconhecimento simples", SPTCarimbo.class);
        adapter.addCategoria("Proctor Normal - Ensaio de compactação", SPTCarimbo.class);
        // todo: passar string pros resources
    }
}