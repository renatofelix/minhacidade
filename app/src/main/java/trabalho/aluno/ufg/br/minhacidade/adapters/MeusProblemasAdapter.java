package trabalho.aluno.ufg.br.minhacidade.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.utils.RecyclerViewProject;

/**
 * Created by Jonathas Eloi
 */

public class MeusProblemasAdapter extends RecyclerViewProject<Problema> {


    public MeusProblemasAdapter() {
        super(R.layout.rv_item_meus, false, false);
    }

    @Override
    public RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent) {
        return new ItemViewHolder(parent.getContext(), inflater.inflate(rootType, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;

        Picasso.get().load(getItem(position).getLinkImagem())
                .fit().into(viewHolder.ivFoto);
//        viewHolder.tvTipoProblema.setText(getItem(position).getTipoProblema().toString());

        switch (getItem(position).getTipoStatus()) {
            case RESOLVIDO:
                viewHolder.ivProcesso.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_check));
                break;
            case REPASSADO:
                viewHolder.ivProcesso.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_arrow_forward));
                break;
            case PENDENTE:
                viewHolder.ivProcesso.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_clock));
                break;
        }

    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {

        private Context context;

//        @BindView(R.id.tvTipoProblema)
//        TextView tvTipoProblema;

        @BindView(R.id.ivFoto)
        ImageView ivFoto;

        @BindView(R.id.ivProcesso)
        ImageView ivProcesso;

        ItemViewHolder(Context context, View v) {
            super(v);
            this.context = context;
            ButterKnife.bind(this, v);
        }
//

        public Context getContext() {
            return context;
        }
    }

}
