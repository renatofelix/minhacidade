package trabalho.aluno.ufg.br.minhacidade.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import trabalho.aluno.ufg.br.minhacidade.ProblemaActivity;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.adapters.ProblemaAdapter;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.utils.GridSpacingItemDecoration;

public class NovosFragment extends Fragment implements ProblemaAdapter.ProblemaAdapterClickListener {

    @BindView(R.id.rv_problemas)
    protected RecyclerView recyclerView;

    ProblemaAdapter problemaAdapter = new ProblemaAdapter();

    List<Problema> problemas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_envios, container, false);
        ButterKnife.bind(this, view);

        problemaAdapter.setProblemaAdapterClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initRV();
    }

    private void initRV() {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(problemaAdapter);

        /// TESTE
        Problema problema = new Problema();
        problema.setTipoProblema(TipoProblema.BURACO);

        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        ///

        //Adicionar problemas no adapter
        problemaAdapter.addAll(problemas);
    }

    @Override
    public void OnClickAbrirProblema(int position) {
        //Abrir activity do problema enviando o problema dessa posicao
        Intent intent = new Intent(getContext(), ProblemaActivity.class);
        intent.putExtra("problema", problemas.get(position));
        startActivity(intent);
    }
}
