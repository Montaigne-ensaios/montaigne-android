package com.montaigne.montaigneandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.montaigne.montaigneandroid.R;

public class  SPTCriar extends AppCompatActivity {
    private String idProjeto, idFuro, nCamada;
    // idProjeto, idFuro = recuperada do intent; nCamada = ultima camada do furo + 1 (criar nova)
    private EditText fieldNAgua, fieldCota;
    private Button btnProxima, btnFinalizar;
    private ImageView imgVoltar, imgHome;
    private EditText[] fieldsGolpes, fieldsPenetras;
    private ImageView[] btnsMenos, btnsMais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_criar);

        idProjeto = getIntent().getStringExtra("idProjeto");
        idFuro = getIntent().getStringExtra("idFuro");

        imgHome = findViewById(R.id.imgSPTCriarHome);
        imgHome.setOnClickListener(view -> {
            startActivity(
                    new Intent(SPTCriar.this, HomeActivity.class));
            finish();
        });

        imgVoltar = findViewById(R.id.imgSPTCriarBack);
        imgVoltar.setOnClickListener(view -> finish());
        // todo: implementar confirmações de usuário nestes botões de saída

        // cria os objetos relativos a golpes e penetrações
        fieldsGolpes = new EditText[]{
                findViewById(R.id.fieldSPTCriarGlp1),
                findViewById(R.id.fieldSPTCriarGlp2),
                findViewById(R.id.fieldSPTCriarGlp3)
        };
        fieldsPenetras = new EditText[]{
                findViewById(R.id.fieldSPTCriarPen1),
                findViewById(R.id.fieldSPTCriarPen2),
                findViewById(R.id.fieldSPTCriarPen3)
        };
        btnsMais = new ImageView[]{
                findViewById(R.id.imgSPTCriarp1),
                findViewById(R.id.imgSPTCriarp2),
                findViewById(R.id.imgSPTCriarp3)
        };
        btnsMenos = new ImageView[]{
                findViewById(R.id.imgSPTCriarm1),
                findViewById(R.id.imgSPTCriarm2),
                findViewById(R.id.imgSPTCriarm3)
        };
        for (int i=0; i<fieldsGolpes.length; i++){
            // configura cada field e image view
            EditText fieldGolpe = fieldsGolpes[i];
            btnsMais[i].setOnClickListener(view -> {
                Integer value = (int) naturalNumberInput(fieldGolpe);
                value++;
                fieldGolpe.setText(value.toString());
                // tentar por o texto como int estava crashando
            });  // listener que recupera o valor dos golpes e altera

            btnsMenos[i].setOnClickListener(view -> {
                Integer value = (int) naturalNumberInput(fieldGolpe);
                if(value > 0) {
                    value--;
                    fieldGolpe.setText(value.toString());
                }
            });  // mesmo que o de cima, só que subtraindo

            fieldGolpe.setOnFocusChangeListener((view, b) -> {
                if(!b){
                    Integer value = (int) naturalNumberInput((EditText) view);
                    ((EditText) view).setText(value.toString());  // isto serve para remover decimais
                }  // trata input se houver alterações manuais
            });

            fieldsPenetras[i]
                    .setOnFocusChangeListener((view, b) -> {
                        if(!b){
                            naturalNumberInput((EditText) view);
                        }
                    });
            // trata input como float
        }

        // cria listeners para tratar alterações das inputs
        fieldCota = findViewById(R.id.fieldSPTCriarCota);
        fieldCota.setOnFocusChangeListener((view, b) -> naturalNumberInput((EditText) view));
        fieldNAgua = findViewById(R.id.fieldSPTCriarAgua);
        fieldNAgua.setOnFocusChangeListener((view, b) -> naturalNumberInput((EditText) view));

        btnProxima = findViewById(R.id.btnSPTCriarProx);
        btnProxima.setOnClickListener(view -> {
            Intent intent = new Intent(SPTCriar.this, SPTCriar.class);
            intent.putExtra("idProjeto", idProjeto);
            intent.putExtra("idFuro", idFuro);
            startActivity(intent);
            finish();
        });
        btnFinalizar = findViewById(R.id.btnSPTCriarFim);
        btnFinalizar.setOnClickListener(view -> finish());
    }
    // todo: adicionar métodos de banco de dados e salvamento antes de finalizar activity

    private static float naturalNumberInput(EditText input){
        // método para tratar inputs numéricas como naturais positivas
        // todo: alterar métodos de correção para mostrar um alerta ao usuário
        String rawValue = input.getText().toString();  // pega o valor do field
        float value;
        try{
            value = Float.parseFloat(rawValue);  // tenta converter para float
        } catch (NumberFormatException e){
            // em caso de erro, seta o valor para zero. Isso talvez deva ser revisitado
            input.setText("0");
            return 0;
        }
        if(value < 0){
            // se o número for negativo, seta o valor para zero
            input.setText("0");
            return 0;
        } else {
            // finalmente, retorna o valor
            return value;
        }
    }
}