package com.montaigne.montaigneandroid.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.dao.AmostraDAO;
import com.montaigne.montaigneandroid.model.Amostra;

public class  SPTCriar extends AppCompatActivity {
    private long idProjeto, idFuro, nCamada;
    // idProjeto, idFuro = recuperada do intent; nCamada = ultima camada do furo + 1 (criar nova)

    private TextView txtSPTCriarTitulo;
    private EditText fieldNAgua, fieldProf;
    private Button btnFinalizar, btnProxima;
    private ImageButton btnVoltar, btnHome, btnSave;
    private EditText[] fieldsGolpes, fieldsPenetras;
    private ImageButton[] btnsMenos, btnsMais;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_criar);

        // recupera dados do intent
        idProjeto = getIntent().getLongExtra("idProjeto", 1);
        idFuro = getIntent().getLongExtra("idFuro", 1);

        // Seria bom ser o id do furo
        nCamada = getIntent().getLongExtra("nCamada", 1);

        txtSPTCriarTitulo = findViewById(R.id.TxtSptCriarTitulodFuro);
        txtSPTCriarTitulo.setText("Ensaio " + nCamada + " -  SPT");

        setupButtons();

        setupFields();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupButtons(){
        btnHome = findViewById(R.id.bntSptCriarHome);
        btnHome.setOnClickListener(v -> {
            startActivity(
                    new Intent(SPTCriar.this, HomeActivity.class));
            finish();
        });

        btnVoltar = findViewById(R.id.BntSPTCriarBackCrbUnico);
        btnVoltar.setOnClickListener(v -> finish());
        // todo: implementar confirma????es de usu??rio nestes bot??es de sa??da

        btnProxima = findViewById(R.id.bntSptCriarProxAmostra);
        btnProxima.setOnClickListener(v -> {
            salvar();

            Intent intent = new Intent(SPTCriar.this, SPTCriar.class);
            intent.putExtra("idProjeto", idProjeto);
            intent.putExtra("idFuro", idFuro);
            intent.putExtra("nCamada", nCamada + 1);
            startActivity(intent);
            finish();
        });

        btnSave = findViewById(R.id.BntSptCriarSalvarFuro);
        btnSave.setOnClickListener(v -> {});
        // todo: salvar ensaio

        btnFinalizar = findViewById(R.id.BntSptCriarFinalizarEnsaio);
        btnFinalizar.setOnClickListener(v -> finish());
        // todo: adicionar m??todos de banco de dados e salvamento antes de finalizar activity
    }

    private void setupFields(){
        // cria os objetos relativos a golpes e penetra????es
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
        btnsMais = new ImageButton[]{
                findViewById(R.id.btnSPTCriarp1),
                findViewById(R.id.btnSPTCriarp2),
                findViewById(R.id.btnSPTCriarp3)
        };
        btnsMenos = new ImageButton[]{
                findViewById(R.id.btnSPTCriarm1),
                findViewById(R.id.btnSPTCriarm2),
                findViewById(R.id.btnSPTCriarm3)
        };

        // configura cada field e image view:
        for (int i=0; i<fieldsGolpes.length; i++){
            EditText fieldGolpe = fieldsGolpes[i];
            btnsMais[i].setOnClickListener(v -> {
                Integer value = (int) naturalNumberInput(fieldGolpe);
                value++;
                fieldGolpe.setText(value.toString());
                // tentar por o texto como int estava crashando
            });  // listener que recupera o valor dos golpes e altera

            btnsMenos[i].setOnClickListener(v -> {
                Integer value = (int) naturalNumberInput(fieldGolpe);
                if(value > 0) {
                    value--;
                    fieldGolpe.setText(value.toString());
                }
            });  // mesmo que o de cima, s?? que subtraindo

            fieldGolpe.setOnFocusChangeListener((v, b) -> {
                if(!b){
                    Integer value = (int) naturalNumberInput((EditText) v);
                    ((EditText) v).setText(value.toString());  // isto serve para remover decimais
                }  // trata input se houver altera????es manuais
            });

            fieldsPenetras[i]
                    .setOnFocusChangeListener((v, b) -> {
                        if(!b){
                            naturalNumberInput((EditText) v);
                        }
                    });
            // trata input como float
        }

        // cria listeners para tratar altera????es das inputs
        fieldProf = findViewById(R.id.fieldSPTCriarProfdadeAmstr);
        fieldProf.setOnFocusChangeListener((v, b) -> naturalNumberInput((EditText) v));
        fieldNAgua = findViewById(R.id.fieldSPTCriarNvdAgua);
        fieldNAgua.setOnFocusChangeListener((v, b) -> naturalNumberInput((EditText) v));
    }

    private static float naturalNumberInput(EditText input){
        // m??todo para tratar inputs num??ricas como naturais positivas
        // todo: alterar m??todos de corre????o para mostrar um alerta ao usu??rio
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
            // se o n??mero for negativo, seta o valor para zero
            input.setText("0");
            return 0;
        } else {
            // finalmente, retorna o valor
            return value;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void salvar() {
        // Capturando dados
        long idSondagem = idFuro;
        int golpes1 = Integer.parseInt(String.valueOf(fieldsGolpes[0].getText()));
        int golpes2 = Integer.parseInt(String.valueOf(fieldsGolpes[1].getText()));
        int golpes3 = Integer.parseInt(String.valueOf(fieldsGolpes[2].getText()));

        int nspt;
        if (golpes3 != 0) {
            nspt = golpes2 + golpes3;
        } else {
            nspt = golpes1 + golpes2;
        }

        Amostra amostra = new Amostra();
        amostra.setIdSondagem(idSondagem);
        amostra.setGolpes1(golpes1);
        amostra.setGolpes2(golpes2);
        amostra.setGolpes3(golpes3);
        amostra.setNspt(nspt);


        // Salvando
        AmostraDAO amostraDAO = new AmostraDAO(getApplicationContext());

        if(amostraDAO.salvar(amostra)) {
            Toast.makeText( getApplicationContext(), "Sucesso ao salvar amostra", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Erro ao salvar amostra", Toast.LENGTH_LONG).show();
        }
    }
}