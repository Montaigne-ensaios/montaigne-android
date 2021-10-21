package com.montaigne.montaigneandroid.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.dao.ProjetoDAO;
import com.montaigne.montaigneandroid.dao.SondagemDAO;
import com.montaigne.montaigneandroid.model.Projeto;
import com.montaigne.montaigneandroid.model.Sondagem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class SPTCarimbo extends AppCompatActivity implements View.OnFocusChangeListener {
    private long idProjeto = -1L;  // long recuperado da intent que diz o id do projeto
    private long idFuro = -1L;
    private HashMap<String, EditText> fields = new HashMap<>();
    private ImageView imgLogoEmpresa, imgSalvar, imgVoltar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spt_carimbo);

        // recupera dados da intent
        idProjeto = getIntent().getLongExtra("idProjeto", -1);

        setupButtons();

        setupFields();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupButtons(){
        imgLogoEmpresa = findViewById(R.id.imgSPTCarimboImg);
        imgLogoEmpresa.setOnClickListener(v -> {
            Toast.makeText(this, "Recurso de imagem precisa ser implementado",
                    Toast.LENGTH_SHORT).show();
            // todo: implementar salvamento da imagem
        });

        imgSalvar = findViewById(R.id.imgSPTCarimboSalvar);
        imgSalvar.setOnClickListener(v -> {
            // todo: implementar salvamento ou mudar o caráter deste botão

            salvar();

            Intent intent = new Intent(SPTCarimbo.this, SPTCriar.class);
            intent.putExtra("idProjeto", idProjeto);
            intent.putExtra("idFuro", idFuro);
            startActivity(intent);
            // todo: adicionar id do novo projeto de acordo com as regras de geração de id

            finish();
            // abre a activity de criação do ensaio e fecha essa
        });

        // botão de voltar
        imgVoltar = findViewById(R.id.imgSPTCarimboBack);
        imgVoltar.setOnClickListener(v -> finish());
        // todo: implementar confirmação de não salvar?
    }

    private void setupFields(){
        // adiciona cada campo de texto a um hashmap
        fields.put("Nome", (EditText) findViewById(R.id.fieldSPTCarimboNome));
        fields.put("Inicio", (EditText) findViewById(R.id.fieldSPTCarimboDataIni));
        fields.put("Empresa", (EditText) findViewById(R.id.fieldSPTCarimboEmpresa));
        fields.put("Tel", (EditText) findViewById(R.id.fieldSPTCarimboTel));
        fields.put("Tecnico", (EditText) findViewById(R.id.fieldSPTCarimboTecnico));
        fields.put("Cliente", (EditText) findViewById(R.id.fieldSPTCarimboCli));
        fields.put("NFuros", (EditText) findViewById(R.id.fieldSPTCarimboNFuros));
        fields.put("Local", (EditText) findViewById(R.id.fieldSPTCarimboNome));

        // adiciona um listener para cada field
        for(Map.Entry<String, EditText> set : fields.entrySet()){
            set.getValue().setOnFocusChangeListener(this);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        /*
        if(!b && !idProjeto.equals("criar")) {
            // salva as alterações quando um field desfoca, se este não for um projeto novo
            // todo: implementar funções da base de dados para salvamentos
            Toast.makeText(this, "Desfoque de View detectado",
                    Toast.LENGTH_SHORT).show();
        }
         */
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void salvar() {
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


        // Identificando dados obrigatórios e salvando
        if (!nome.equals("")) {
            // Criando objeto
            Projeto meuProjeto = new Projeto();

            meuProjeto.setNome(nome);
            meuProjeto.setCliente(cliente);
            meuProjeto.setEmpresa(empresa);
            meuProjeto.setTelefone(telefone);
            meuProjeto.setTecnicoResponsavel(tecnico);
            meuProjeto.setEndereco(endereco);
            meuProjeto.setNumeroFuros(nfuros);


            // Capturando tempo atual ou o dado pelo usuário
            Date tempo = new Date();

            try {
                tempo = formato.parse(dataInicio);

            } catch (Exception e) {
                Long milissegundos = System.currentTimeMillis();
            }

            meuProjeto.setDataInicio(tempo);

            // Executa Salvar
            ProjetoDAO projetoDAO = new ProjetoDAO(getApplicationContext());

            if (projetoDAO.salvar(meuProjeto)) {
                Toast.makeText( getApplicationContext(), "Sucesso ao salvar projeto", Toast.LENGTH_LONG).show();

                idProjeto = projetoDAO.pesquisar(meuProjeto.getNome());

                Sondagem minhaSondagem = new Sondagem();
                minhaSondagem.setIdSpt( idProjeto );
                minhaSondagem.setNumero(1);
                minhaSondagem.setNivelDAgua(0);
                minhaSondagem.setNivelFuro(0);
                minhaSondagem.setNivelReferencia(0);
                minhaSondagem.setTotalPerfurado(0);
                minhaSondagem.setCoordenada("x");
                minhaSondagem.setDataInicio( meuProjeto.getDataInicio() );

                SondagemDAO sondagemDAO = new SondagemDAO(getApplicationContext());

                if (sondagemDAO.salvar(minhaSondagem)) {
                    Toast.makeText( getApplicationContext(), "Sucesso ao salvar sondagem", Toast.LENGTH_LONG).show();

                    // Pegando o id do primeiro furo do projeto
                    idFuro = sondagemDAO.pesquisar( idProjeto ).get(0);

                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao salvar sondagem", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Erro ao salvar projeto", Toast.LENGTH_LONG).show();
            }


        } else {
            // Mensagem de aviso sobre o nome
            Toast.makeText(getApplicationContext(), "Insira um nome", Toast.LENGTH_LONG).show();
        }
    }
}