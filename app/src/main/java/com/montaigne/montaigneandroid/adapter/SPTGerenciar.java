package com.montaigne.montaigneandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.activity.SPTProjeto;

import java.util.ArrayList;

public class SPTGerenciar extends RecyclerView.Adapter<SPTGerenciar.ViewHolder>{
    // em caso de dúvidas, ver comentários do adapeter Home Categorias
    private Context context;
    private ArrayList<String> nomes, descs, ids;  // ids são os ids de cada projeto

    public SPTGerenciar(Context context){
        this.context= context;
        nomes = new ArrayList<>();
        descs = new ArrayList<>();
        ids = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_spt_gerenciar_projetos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNome.setText(nomes.get(position));
        holder.txtDesc.setText(descs.get(position));

        holder.parent.setOnClickListener(v -> {
                Intent intent = new Intent(context, SPTProjeto.class);
                intent.putExtra("idProjeto", ids.get(position));
                // redireciona para a edição do ensaio, passando o id, sem fechar a activity
                context.startActivity(intent);
                // todo: alterar este listener com o gerenciamento de seleções
            }
        );

        // todo: gerenciamento de seleções (criar issue)
    }

    @Override
    public int getItemCount() { return nomes.size();}

    public void addEnsaio(String nome, String desc, String idProjeto){
        nomes.add(nome);
        descs.add(desc);
        ids.add(idProjeto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtNome, txtDesc;
        private CheckBox checkBox;
        private boolean selected = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.cardSPTGerenciar);
            parent.setOnLongClickListener(v -> listenerSelection());
            // listener para selecionar e desselecionar ensaios (para gerenciamento)

            checkBox = itemView.findViewById(R.id.checkSelectSPTGerenciar);
            txtNome = itemView.findViewById(R.id.txtSPTFurosNome);
            txtDesc = itemView.findViewById(R.id.txtSPTGenrenciarDesc);
        }

        private boolean listenerSelection(){
            // Altera a visibilidade da checkBox para inidcar se está selecionado ou não e
            // muda o valor da variável
            if(selected){
                checkBox.setVisibility(View.GONE);
            } else {
                checkBox.setVisibility(View.VISIBLE);
            }  // todo: configurar vibração do celular quando seleciona (discutir)
            selected = !selected;
            return selected;
        }
    }
}
