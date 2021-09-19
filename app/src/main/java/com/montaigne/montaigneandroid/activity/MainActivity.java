package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import com.montaigne.montaigneandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = findViewById(R.id.main_layout);
        layout.setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, HomeActivity.class)
            )
        );  // intent para a HomeActivity
        // todo: fazer com que não dê para retornar para essa tela
    }
}