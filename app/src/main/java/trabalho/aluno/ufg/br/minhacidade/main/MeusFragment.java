package trabalho.aluno.ufg.br.minhacidade.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.adapters.MeusProblemasAdapter;
import trabalho.aluno.ufg.br.minhacidade.adapters.ProblemaAdapter;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.utils.GridSpacingItemDecoration;

public class MeusFragment extends Fragment {

    @BindView(R.id.rvMeusEnvios)
    protected RecyclerView recyclerView;

    MeusProblemasAdapter meusProblemasAdapter = new MeusProblemasAdapter();

    private List<Problema> problemas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_meus_envios, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initRV();
    }

    private void initRV() {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(meusProblemasAdapter);

        /// TESTE
        Problema problema = new Problema();

        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        problemas.add(problema);
        ///

        //Adicionar problemas no adapter
        meusProblemasAdapter.addAll(problemas);
    }

}
