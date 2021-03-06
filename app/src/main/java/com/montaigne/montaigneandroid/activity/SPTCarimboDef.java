package com.montaigne.montaigneandroid.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.dao.ProjetoDAO;
import com.montaigne.montaigneandroid.dao.SondagemDAO;
import com.montaigne.montaigneandroid.model.Projeto;
import com.montaigne.montaigneandroid.model.Sondagem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SPTCarimboDef extends AppCompatActivity implements View.OnFocusChangeListener {
    private long idProjeto = -1L;  // long recuperado da intent que diz o id do projeto
    private long idFuro = -1L;
    private HashMap<String, EditText> fields = new HashMap<>();
    private ImageView imgLogoEmpresa;
    private ImageButton btnVoltar, btnSalvar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_carimbo_def);

        // recupera dados da intent
        idProjeto = getIntent().getLongExtra("idProjeto", -1);

        setupButtons();

        setupFields();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupButtons(){
        imgLogoEmpresa = findViewById(R.id.imgSPTCarimboDefImg);
        imgLogoEmpresa.setOnClickListener(v -> {
            Toast.makeText(this, "Recurso de imagem precisa ser implementado",
                    Toast.LENGTH_SHORT).show();
            // todo: implementar salvamento da imagem
        });

        btnSalvar = findViewById(R.id.btnSPTCarimboDefSalvar);
        btnSalvar.setOnClickListener(v -> {
            // todo: implementar salvamento ou mudar o car??ter deste bot??o

            if(salvar()) {
                Intent intent = new Intent(SPTCarimboDef.this, SPTCriar.class);
                intent.putExtra("idProjeto", idProjeto);
                intent.putExtra("idFuro", idFuro);
                intent.putExtra("nCamada", 1);
                startActivity(intent);
                // todo: adicionar id do novo projeto de acordo com as regras de gera????o de id

                finish();
                // abre a activity de cria????o do ensaio e fecha essa
            }

        });

        // bot??o de voltar
        btnVoltar = findViewById(R.id.btnSPTCarimboDefBack);
        btnVoltar.setOnClickListener(v -> finish());
        // todo: implementar confirma????o de n??o salvar?
    }

    private void setupFields(){
        // adiciona cada campo de texto a um hashmap
        fields.put("Nome", (EditText) findViewById(R.id.fieldSPTCarimboDefNome));
        fields.put("Inicio", (EditText) findViewById(R.id.fieldSPTCarimboDefDataIni));
        fields.put("Empresa", (EditText) findViewById(R.id.fieldSPTCarimboDefEmpresa));
        fields.put("Tel", (EditText) findViewById(R.id.fieldSPTCarimboDefTel));
        fields.put("Tecnico", (EditText) findViewById(R.id.fieldSPTCarimboDefTecnico));
        fields.put("Cliente", (EditText) findViewById(R.id.fieldSPTCarimboDefCli));
        fields.put("NFuros", (EditText) findViewById(R.id.fieldSPTCarimboDefNFuros));
        fields.put("Local", (EditText) findViewById(R.id.fieldSPTCarimboDefNome));

        // adiciona um listener para cada field
        for(Map.Entry<String, EditText> set : fields.entrySet()){
            set.getValue().setOnFocusChangeListener(this);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        /*
        if(!b && !idProjeto.equals("criar")) {
            // salva as altera????es quando um field desfoca, se este n??o for um projeto novo
            // todo: implementar fun????es da base de dados para salvamentos
            Toast.makeText(this, "Desfoque de View detectado",
                    Toast.LENGTH_SHORT).show();
        }
         */
    }


    // todo: simplificar o m??todo
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean salvar() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        // Capturando dados
        String nome = fields.get("Nome").getText().toString();
        String cliente = fields.get("Cliente").getText().toString();
        String empresa = fields.get("Empresa").getText().toString();
        String telefone = fields.get("Tel").getText().toString();
        String tecnico = fields.get("Tecnico").getText().toString();
        String endereco = fields.get("Local").getText().toString();

        int nfuros = Integer.parseInt(fields.get("NFuros").getText().toString());
        String dataInicio = fields.get("Inicio").getText().toString();


        // Identificando dados obrigat??rios e salvando
        if (!nome.equals("") && nfuros > 0) {
            // Criando objeto
            Projeto meuProjeto = new Projeto();

            meuProjeto.setNome(nome);
            meuProjeto.setCliente(cliente);
            meuProjeto.setEmpresa(empresa);
            meuProjeto.setTelefone(telefone);
            meuProjeto.setTecnicoResponsavel(tecnico);
            meuProjeto.setEndereco(endereco);
            meuProjeto.setNumeroFuros(nfuros);


            // Capturando tempo atual ou o dado pelo usu??rio
            Date tempo = new Date();

            try {
                tempo = formato.parse(dataInicio);
            } catch (Exception e) {
                e.printStackTrace();
            }

            meuProjeto.setDataInicio(tempo);

            // Salvando
            ProjetoDAO projetoDAO = new ProjetoDAO(getApplicationContext());
            SondagemDAO sondagemDAO = new SondagemDAO(getApplicationContext());

            try {
                if (projetoDAO.salvar(meuProjeto)) {

                    // Pegando o id do projeto e do primeiro furo do projeto
                    idProjeto = projetoDAO.pesquisar(meuProjeto.getNome());

                    meuProjeto.setId(idProjeto);
                    criarSondagens(meuProjeto.getNumeroFuros(), meuProjeto);

                    idFuro = sondagemDAO.pesquisar( idProjeto ).get(0).getId();

                    Log.i("furos", sondagemDAO.pesquisar( idProjeto ).size() + "");

                    Toast.makeText( getApplicationContext(), "Sucesso ao salvar projeto", Toast.LENGTH_LONG).show();
                    return true;

                } else {
                    throw new Exception("Erro ao salvar projeto.");
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        } else {
            // Mensagem de aviso sobre o nome
            Toast.makeText(getApplicationContext(), "Falta de dados.", Toast.LENGTH_LONG).show();
        }

        return false;
    }


    public void criarSondagens(int quantidade, Projeto projeto) throws Exception {

        for ( int i = 0; i < quantidade; i++) {
            Sondagem sondagem = new Sondagem();
            sondagem.setIdSpt( projeto.getId() );
            sondagem.setNumero(i + 1);
            sondagem.setNivelDAgua(0);
            sondagem.setNivelFuro(0);
            sondagem.setNivelReferencia(0);
            sondagem.setTotalPerfurado(0);
            sondagem.setCoordenada("x");
            sondagem.setDataInicio( projeto.getDataInicio() );

            SondagemDAO sondagemDAO = new SondagemDAO(getApplicationContext());

            if (!sondagemDAO.salvar(sondagem)) {
                // Caso haja erro ao salvar sondagem, ser?? lan??ado um erro
                throw new Exception("Erro ao salvar sondagem.");
            }
        }
    }
}