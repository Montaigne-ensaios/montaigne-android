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

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeCategorias adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adapter = new HomeCategorias(this);
        recyclerView = findViewById(R.id.recyclerCategorias);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("SPT - Ensaio de reconhecimento simples");
        nomes.add("Proctor Normal - Ensaio de compactação"); // todo: adicionar strings nos resources
        adapter.setNomes(nomes);
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