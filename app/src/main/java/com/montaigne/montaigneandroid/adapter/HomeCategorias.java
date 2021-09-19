package com.montaigne.montaigneandroid.adapter;

//import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
//import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.montaigne.montaigneandroid.R;

import java.util.ArrayList;

public class HomeCategorias extends RecyclerView.Adapter<HomeCategorias.ViewHolder> {
    // adapter para gerar os itens do recycler view da HomeActivity
    private ArrayList<String> nomes;
    private Context context;

    public HomeCategorias(Context context){
        this.context = context;  // passa o contexto da tela em que o layout é criado
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // cria as views do layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home_categorias, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // configura cada view do recycler de a cordo com suas posições
        holder.txtCategoriaNome.setText(nomes.get(position));
        // seta o texto do txtViewCategoriaNome de acorodo com a posição da lista nomes
        holder.parent.setOnClickListener(view -> // startActivity(
                // new Intent(context, SPTCarimbo.class)
                // )
                Toast.makeText(context, "Foi clicado", Toast.LENGTH_SHORT).show()
        );  // todo: corrigir o Intent (não to conseguindo passar o context)
    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }

    public void setNomes(ArrayList<String> nomes) { this.nomes = nomes; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView txtCategoriaNome;

        public ViewHolder(@NonNull View itemView) {
            // seta objetos de cada view
            super(itemView);
            parent = itemView.findViewById(R.id.cardHomeCategorias);
            txtCategoriaNome = itemView.findViewById(R.id.txtCategoriasNome);
        }
    }
}
