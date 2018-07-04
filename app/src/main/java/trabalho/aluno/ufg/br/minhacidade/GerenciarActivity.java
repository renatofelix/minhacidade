package trabalho.aluno.ufg.br.minhacidade;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.adapters.ProblemaAdapter;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.utils.GridSpacingItemDecoration;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;
import trabalho.aluno.ufg.br.minhacidade.web.WebTaskProblema;

public class GerenciarActivity extends AppCompatActivity implements ProblemaAdapter.ProblemaAdapterClickListener {

    @BindView(R.id.rv_problemas)
    protected RecyclerView recyclerView;

    private Dialog alertDialog;

    ProblemaAdapter problemaAdapter = new ProblemaAdapter();

    List<Problema> problemas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar);
        ButterKnife.bind(this);

        problemaAdapter.setProblemaAdapterClickListener(this);

        initRV();
        pegarProblemas();
    }

    @OnClick(R.id.tvSelecionarTipoProblema)
    public void selecionarTipoProblema(View view) {
        alertDialog = Utils.onCreateSelectDialog(this, R.string.strTituloTipoProblema, R.array.tiposProblemas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //salvar tipo na variavel problema
                switch (i) {
                    case 0:
                        //Buscar problema do tipo TipoProblema.FALTA_SINALIZACAO
                        break;
                    case 1:
                        //Buscar problema do tipo TipoProblema.ENTULHO_TERRENO_BALDIO
                        break;
                    case 2:
                        //Buscar problema do tipo TipoProblema.BURACO
                        break;
                    case 3:
                        //Buscar problema do tipo TipoProblema.FALTA_ILUMINACAO
                        break;
                    case 4:
                        //Buscar problema do tipo TipoProblema.NAO_RECOLHERAM_LIXO
                        break;
                }
            }
        });
        alertDialog.show();
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
//        hideLoading();

        problemas.addAll(response);

        //Adicionar problemas no adapter
        problemaAdapter.clear();
        problemaAdapter.addAll(problemas);
    }

    private void initRV() {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(problemaAdapter);
    }

    private void pegarProblemas() {
        WebTaskProblema taskProblemas = new WebTaskProblema(this);
        taskProblemas.execute();
    }

    @Override
    public void OnClickAbrirProblema(int position) {
        //Abrir activity do problema enviando o problema dessa posicao
        Intent intent = new Intent(this, EdicaoProblemaAdmActivity.class);
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
