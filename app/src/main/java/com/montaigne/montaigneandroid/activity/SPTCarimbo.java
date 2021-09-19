package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.montaigne.montaigneandroid.R;

import java.util.HashMap;
import java.util.Map;

public class SPTCarimbo extends AppCompatActivity implements View.OnFocusChangeListener {
    private HashMap<String, EditText> fields = new HashMap<>();
    private ImageView imgLogoEmpresa, imgSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carimbo_spt);

        imgLogoEmpresa = findViewById(R.id.imgSPTCarimboImg);
        imgLogoEmpresa.setOnClickListener(view -> {
            Toast.makeText(this, "Recurso de imagem precisa ser implementado",
                    Toast.LENGTH_SHORT).show();
            // todo: implementar salvamento de imagem
        });

        imgSalvar = findViewById(R.id.imgSPTCarimboSalvar);
        imgSalvar.setOnClickListener(view -> {
            // todo: implementar salvamento ou mudar o caráter deste botão
            startActivity(new Intent(SPTCarimbo.this, SPTCriar.class));
            finish();
            // abre a activity de criação do ensaio e fecha essa
        });
        
        // adiciona cada campo de texto a um hashmap
        fields.put("Nome", (EditText) findViewById(R.id.fieldSPTCarimboNome));
        fields.put("Inicio", (EditText) findViewById(R.id.fieldSPTCarimboDataIni));
        fields.put("Empresa", (EditText) findViewById(R.id.fieldSPTCarimboEmpresa));
        fields.put("Tel", (EditText) findViewById(R.id.fieldSPTCarimboTel));
        fields.put("Tecnico", (EditText) findViewById(R.id.fieldSPTCarimboTecnico));
        fields.put("Clinte", (EditText) findViewById(R.id.fieldSPTCarimboCli));
        fields.put("NFuros", (EditText) findViewById(R.id.fieldSPTCarimboNFuros));
        fields.put("Local", (EditText) findViewById(R.id.fieldSPTCarimboNome));

        for(Map.Entry<String, EditText> set : fields.entrySet()){
            set.getValue().setOnFocusChangeListener(this);
        }
        // adiciona um listener para cada field

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(!b) {
            // todo: implementar funções da base de dados para salvamentos
            Toast.makeText(this, "Desfoque de View detectado",
                    Toast.LENGTH_SHORT).show();
        }
    }
}