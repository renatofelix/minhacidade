package trabalho.aluno.ufg.br.minhacidade.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import trabalho.aluno.ufg.br.minhacidade.ProblemaActivity;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.adapters.ProblemaAdapter;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;
import trabalho.aluno.ufg.br.minhacidade.utils.GridSpacingItemDecoration;
import trabalho.aluno.ufg.br.minhacidade.web.WebError;
import trabalho.aluno.ufg.br.minhacidade.web.WebTaskLogin;
import trabalho.aluno.ufg.br.minhacidade.web.WebTaskProblema;

public class NovosFragment extends Fragment implements ProblemaAdapter.ProblemaAdapterClickListener {

    @BindView(R.id.rv_problemas)
    protected RecyclerView recyclerView;

    private FloatingActionButton fabAdicionarProblema;
    private boolean usuarioLogado = false;
    private Usuario usuario = new Usuario();

    ProblemaAdapter problemaAdapter = new ProblemaAdapter();
    MaterialDialog dialog;

    List<Problema> problemas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_envios, container, false);
        ButterKnife.bind(this, view);

        verificaUsuarioLogado();
        problemaAdapter.setProblemaAdapterClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(List<Problema> response){
        hideLoading();

        problemas.addAll(response);

        //Adicionar problemas no adapter
        problemaAdapter.clear();
        problemaAdapter.addAll(problemas);
    }

    private void pegarProblemas() {
        WebTaskProblema taskProblemas = new WebTaskProblema(getContext());
        taskProblemas.execute();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(getContext())
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        fabAdicionarProblema = ((MainActivity)getActivity()).fabAdicionarProblema;
        initRV();
    }

    @Override
    public void onResume() {
        super.onResume();
        pegarProblemas();
    }

    private void initRV() {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(problemaAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (usuarioLogado) {
                    if (dy > 0 && fabAdicionarProblema.getVisibility() == View.VISIBLE) {
                        fabAdicionarProblema.hide();
                    } else if (dy < 0 && fabAdicionarProblema.getVisibility() != View.VISIBLE) {
                        fabAdicionarProblema.show();
                    }
                }
            }
        });
    }

    private void verificaUsuarioLogado() {
        //TODO: verificar no sharedpreferences se a variavel de usuaro logado e o id existe, se sim ele esta logado
        //TODO: se tiver logado colocar as informações no objeto usuario

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String id = sharedPref.getString(getString(R.string.id),null);

        if(id!=null)
        {
            String senha = sharedPref.getString(getString(R.string.senha),null);
            String login = sharedPref.getString(getString(R.string.email),null);
            String userType = sharedPref.getString(getString(R.string.usertype),null);

            usuario.setLogin(login);
            usuario.setId(id);
            usuario.setUsertype(userType);
            usuario.setPassword(senha);

            usuarioLogado = true;
        }
    }

    @Override
    public void OnClickAbrirProblema(int position) {
        //Abrir activity do problema enviando o problema dessa posicao
        Intent intent = new Intent(getContext(), ProblemaActivity.class);
        intent.putExtra("problema", problemas.get(position));
        startActivity(intent);
    }

    @Override
    public void OnClickAbrirLocalizacao(int position) {
        String geo = "geo:0,0?q=" + problemas.get(position).getLocalizacao() + "(" + problemas.get(position).getEndereco() + ")";
        Uri gmmIntentUri = Uri.parse(geo);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
