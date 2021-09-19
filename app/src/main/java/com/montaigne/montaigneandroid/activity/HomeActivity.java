package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // adiciona elementos ao recycler (isso talvez seja movido para outro lugar):
        adapter.addCategoria("SPT - Ensaio de reconhecimento simples", SPTCarimbo.class);
        adapter.addCategoria("Proctor Normal - Ensaio de compactação", SPTCarimbo.class);
        // todo: passar string pros resources
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case R.id.itemProjetos:

        Toast.makeText(HomeActivity.this,"montaigne.ensaios@protonmail.com",Toast.LENGTH_SHORT).show();}
       return super.onOptionsItemSelected(item);
    }
}