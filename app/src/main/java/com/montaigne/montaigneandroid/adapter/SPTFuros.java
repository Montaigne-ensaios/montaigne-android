package com.montaigne.montaigneandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;
import com.montaigne.montaigneandroid.activity.SPTEditar;

import java.util.ArrayList;

public class SPTFuros extends RecyclerView.Adapter<SPTFuros.ViewHolder> {
    // em caso de dúvidas consultar comentários de HomeCategorias
    private Context context;
    private String idProjeto;  // id do projeto ao qual os furos pertecem
    private ArrayList<String> nomes, ids;  // nomes e ids dos furos
    // todo: substituir o nome por um único id

    public SPTFuros(Context context, String idProjeto){
        this.context = context;
        this.idProjeto = idProjeto;
        nomes = new ArrayList<>();
        ids = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_spt_furos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nome.setText(nomes.get(position));

        holder.nome.setOnClickListener(v -> {
                Intent intent = new Intent(context, SPTEditar.class);
                intent.putExtra("idProjeto", idProjeto);
                intent.putExtra("idFuro", ids.get(position));
                // passa o id do projeto e o furo selecionado para a activity de edição de ensaio
                context.startActivity(intent);
            }
        );
    }

    @Override
    public int getItemCount() { return nomes.size();}

    public void addEnsaio(String nome, String id){
        nomes.add(nome);
        ids.add(id);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView nome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBoxSPTFuros);  // todo: gerenciamento de furos
            nome = itemView.findViewById(R.id.txtSPTFurosNome);
        }
    }
}
