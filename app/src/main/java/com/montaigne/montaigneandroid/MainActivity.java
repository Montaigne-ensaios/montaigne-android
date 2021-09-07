package com.montaigne.montaigneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = findViewById(R.id.main_layout);
        layout.setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, TelaInicial.class)
            )
        );
    }
}