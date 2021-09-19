package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.montaigne.montaigneandroid.R;

public class SPTCarimbo extends AppCompatActivity implements View.OnFocusChangeListener {
    private EditText fieldNome, fieldIni, fieldEmpresa, fieldTel, fieldTecnico, fieldCli,
            fieldNFuros, fieldLocal;
    private ImageView imgLogoEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carimbo_spt);

        fieldNome = findViewById(R.id.fieldSPTCarimboNome);
        fieldIni = findViewById(R.id.fieldSPTCarimboDataIni);
        fieldEmpresa = findViewById(R.id.fieldSPTCarimboEmpresa);
        fieldTel = findViewById(R.id.fieldSPTCarimboTel);
        fieldTecnico = findViewById(R.id.fieldSPTCarimboTecnico);
        fieldCli = findViewById(R.id.fieldSPTCarimboCli);
        fieldNFuros = findViewById(R.id.fieldSPTCarimboNFuros);
        fieldLocal = findViewById(R.id.fieldSPTCarimboNome);

    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
}