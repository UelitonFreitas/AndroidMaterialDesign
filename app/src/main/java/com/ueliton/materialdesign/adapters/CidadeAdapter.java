package com.ueliton.materialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ueliton.materialdesign.modelos.Cidade;
import com.ueliton.materialdesign.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ueliton on 04/08/2015.
 */
public class CidadeAdapter extends RecyclerView.Adapter<CidadeAdapter.MinhaViewHolder>{


    private LayoutInflater mInflater;
    private List<Cidade> listaDeCidades = Collections.emptyList();

    public CidadeAdapter(Context contexto, List<Cidade> cidades) {
        mInflater = LayoutInflater.from(contexto);
        listaDeCidades = cidades;
    }

    @Override
    public CidadeAdapter.MinhaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =  mInflater.inflate(R.layout.linha_customisada, parent, false);

        MinhaViewHolder minhaViewHolder = new MinhaViewHolder(view);

        return minhaViewHolder;
    }

    @Override
    public void onBindViewHolder(CidadeAdapter.MinhaViewHolder holder, int position) {

        Cidade cidadeAtual = listaDeCidades.get(position);

        holder.texto.setText(cidadeAtual.getNome());
        holder.icone.setImageResource(cidadeAtual.getIdIcone());
    }

    @Override
    public int getItemCount() {
        return listaDeCidades.size();
    }

    class MinhaViewHolder extends RecyclerView.ViewHolder{

        TextView texto;
        ImageView icone;

        public MinhaViewHolder(View itemView) {
            super(itemView);
            texto = (TextView) itemView.findViewById(R.id.tv_texto_da_lista);
            icone = (ImageView) itemView.findViewById(R.id.iv_icone_da_lista);
        }
    }
}
