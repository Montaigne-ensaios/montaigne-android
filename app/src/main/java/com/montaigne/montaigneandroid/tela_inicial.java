package com.montaigne.montaigneandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class tela_inicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //getMenuInflater().inflate();

        return super.onCreateOptionsMenu(menu);
    }]
}
}