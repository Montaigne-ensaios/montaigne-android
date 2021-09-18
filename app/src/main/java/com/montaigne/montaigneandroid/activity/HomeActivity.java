package com.montaigne.montaigneandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.montaigne.montaigneandroid.databinding.ActivityCarimboSptBinding;
import com.montaigne.montaigneandroid.R;

public class HomeActivity extends AppCompatActivity {



    TextView textView;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView= (TextView) findViewById(R.id.textView8);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ActivityCarimboSptBinding.class);
                startActivity(intent);
                   }
        });

        botao = findViewById(R.id.button3);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(HomeActivity.this, ActivityCarimboSptBinding.class);
                startActivity(Intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
        case R.id.itemProjetos:

        Toast.makeText(HomeActivity.this,"montaigne.ensaios@protonmail.com",Toast.LENGTH_SHORT).show();}
       return super.onOptionsItemSelected(item);
    }
}