package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.montaigne.montaigneandroid.R;

public class HomeActivity extends AppCompatActivity {
    TextView textView;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView= (TextView) findViewById(R.id.textView8);
        textView.setOnClickListener(view -> startActivity(
                new Intent(HomeActivity.this, SPTCarimbo.class)
                )
        );

        botao = findViewById(R.id.button3);
        botao.setOnClickListener(view -> startActivity(
                new Intent(HomeActivity.this, SPTCarimbo.class)
                )
        );
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