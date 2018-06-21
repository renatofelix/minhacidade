package trabalho.aluno.ufg.br.minhacidade.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.utils.RecyclerViewProject;

/**
 * Created by Jonathas Eloi
 */

public class ProblemaAdapter extends RecyclerViewProject<Problema> {

    private ProblemaAdapterClickListener problemaAdapterClickListener;

    public void setProblemaAdapterClickListener(ProblemaAdapterClickListener problemaAdapterClickListener) {
        this.problemaAdapterClickListener = problemaAdapterClickListener;
    }

    public interface ProblemaAdapterClickListener {
        void OnClickAbrirProblema(int position);
    }

    public ProblemaAdapter() {
        super(R.layout.rv_item_envios, false, false);
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

        viewHolder.tvTipoProblema.setText(getItem(position).getTipoProblema().toString());

    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        @BindView(R.id.tvTipoProblema)
        TextView tvTipoProblema;

        @OnClick(R.id.rlProblema)
        public void onClickEvento(){
            problemaAdapterClickListener.OnClickAbrirProblema(getAdapterPosition());
        }

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
