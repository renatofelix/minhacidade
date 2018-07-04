package trabalho.aluno.ufg.br.minhacidade.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.adapters.MeusProblemasAdapter;
import trabalho.aluno.ufg.br.minhacidade.adapters.ProblemaAdapter;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;
import trabalho.aluno.ufg.br.minhacidade.utils.GridSpacingItemDecoration;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;
import trabalho.aluno.ufg.br.minhacidade.web.WebTaskProblema;

public class MeusFragment extends Fragment {

    @BindView(R.id.rvMeusEnvios)
    protected RecyclerView recyclerView;

    @BindView(R.id.tvSelecionarTipoProblema)
    protected TextView tvSelecionarTipoProblema;

    MeusProblemasAdapter meusProblemasAdapter = new MeusProblemasAdapter();

    private List<Problema> problemas = new ArrayList<>();
    MaterialDialog dialog;

    private Usuario usuario = new Usuario();
    private boolean usuarioLogado = false;
    private Dialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_meus_envios, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        verificaUsuarioLogado();
        initRV();
    }

    @Override
    public void onResume() {
        super.onResume();
        pegarProblemas();
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

        //Adicionar problemas no adapter
        meusProblemasAdapter.clear();
        meusProblemasAdapter.addAll(response);
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
    }

    private void initRV() {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(meusProblemasAdapter);

        /// TESTE
        Problema problema = new Problema();
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

    private void pegarProblemas() {
        WebTaskProblema taskProblemas = new WebTaskProblema(getContext());
        taskProblemas.execute();
    }

    @OnClick(R.id.tvTipoProblema)
    public void selecionarTipoProblema(View view) {
        alertDialog = Utils.onCreateSelectDialog(getActivity(), R.string.strTituloTipoProblema, R.array.tiposProblemas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //salvar tipo na variavel problema
                switch (i) {
                    case 0:
                        tvSelecionarTipoProblema.setText(TipoProblema.FALTA_SINALIZACAO.toString());
                        break;
                    case 1:
                        tvSelecionarTipoProblema.setText(TipoProblema.ENTULHO_TERRENO_BALDIO.toString());
                        break;
                    case 2:
                        tvSelecionarTipoProblema.setText(TipoProblema.BURACO.toString());
                        break;
                    case 3:
                        tvSelecionarTipoProblema.setText(TipoProblema.FALTA_ILUMINACAO.toString());
                        break;
                    case 4:
                        tvSelecionarTipoProblema.setText(TipoProblema.NAO_RECOLHERAM_LIXO.toString());
                        break;
                }
            }
        });
        alertDialog.show();
    }
}
