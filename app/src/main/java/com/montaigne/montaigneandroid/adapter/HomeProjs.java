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
import com.montaigne.montaigneandroid.model.Projeto;

import java.util.ArrayList;
import java.util.List;

public class HomeProjs extends RecyclerView.Adapter<HomeProjs.ViewHolder>{
    // em caso de dúvidas, ver comentários do adapeter Home Categorias
    private Context context;
    private ArrayList<String> nomes, descs, ids;  // ids são os ids de cada projeto
    private List<Projeto> projetos;

    public HomeProjs(Context context){
        this.context= context;
        nomes = new ArrayList<>();
        descs = new ArrayList<>();
        ids = new ArrayList<>();
    }

    public HomeProjs(Context context, List<Projeto> projetos){
        this.context= context;
        nomes = new ArrayList<>();
        descs = new ArrayList<>();
        ids = new ArrayList<>();

        this.projetos = projetos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home_projs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*
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
         */

        holder.txtNome.setText(projetos.get(position).getNome());
        holder.txtDesc.setText(projetos.get(position).getEndereco());

        holder.parent.setOnClickListener(v -> {
                    Intent intent = new Intent(context, SPTProjeto.class);
                    intent.putExtra("idProjeto", projetos.get(position).getId());
                    // redireciona para a edição do ensaio, passando o id, sem fechar a activity
                    context.startActivity(intent);
                    // todo: alterar este listener com o gerenciamento de seleções
                }
        );

        // todo: gerenciamento de seleções (criar issue)


    }

    @Override
    public int getItemCount() {
        //return nomes.size();
        return projetos.size();
        }

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

            parent = itemView.findViewById(R.id.cardProjs);
            parent.setOnLongClickListener(v -> listenerSelection());
            // listener para selecionar e desselecionar ensaios (para gerenciamento)

            checkBox = itemView.findViewById(R.id.checkSelectProjs);
            txtNome = itemView.findViewById(R.id.txtProjsNome);
            txtDesc = itemView.findViewById(R.id.txtProjsDesc);
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
