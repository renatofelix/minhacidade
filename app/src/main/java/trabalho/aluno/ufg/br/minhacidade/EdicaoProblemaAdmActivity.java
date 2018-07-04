package trabalho.aluno.ufg.br.minhacidade;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoStatus;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class EdicaoProblemaAdmActivity extends AppCompatActivity {

    Problema problema = null;

    @BindView(R.id.tvAntes)
    protected TextView tvAntes;

    @BindView(R.id.tvSelecionarTipoProblema)
    protected TextView tvSelecionarTipoProblema;

    @BindView(R.id.etComentario)
    protected EditText etComentario;

    @BindView(R.id.tvSelecionarStatusProblema)
    protected TextView tvSelecionarStatusProblema;

    @BindView(R.id.ivFotoAntes)
    protected ImageView ivFotoAntes;

    @BindView(R.id.ivFotoDepois)
    protected ImageView ivFotoDepois;

    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_problema_adm);
        ButterKnife.bind(this);

        problema = getIntent().getParcelableExtra("problema");

        if (problema != null) {
//            initViews(problema);
            initDados(problema);
        }
    }

    @OnClick(R.id.btnResponder)
    public void responder(View view) {

    }

    private void initDados(Problema problema) {
        //Colcoar as views da tela para receber os dados doobjeto problema
        tvSelecionarTipoProblema.setText(problema.getTipoProblema().toString());
        etComentario.setText(problema.getComentario());

        Picasso.get().load(problema.getLinkImagem()).fit().into(ivFotoAntes);
        if (ivFotoDepois.getVisibility() == View.VISIBLE) {
            Picasso.get().load(problema.getLinkImagem()).fit().into(ivFotoDepois);
        }

        tvSelecionarStatusProblema.setText(problema.getTipoStatus().toString());
    }

    @OnClick(R.id.tvSelecionarTipoProblema)
    public void selecionarTipoProblema(View view) {
        alertDialog = Utils.onCreateSelectDialog(this, R.string.strTituloTipoProblema, R.array.tiposProblemas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //salvar tipo na variavel problema
                switch (i) {
                    case 0:
                        tvSelecionarTipoProblema.setText(TipoProblema.FALTA_SINALIZACAO.toString());
                        problema.setTipoProblema(TipoProblema.FALTA_SINALIZACAO);
                        break;
                    case 1:
                        tvSelecionarTipoProblema.setText(TipoProblema.ENTULHO_TERRENO_BALDIO.toString());
                        problema.setTipoProblema(TipoProblema.ENTULHO_TERRENO_BALDIO);
                        break;
                    case 2:
                        tvSelecionarTipoProblema.setText(TipoProblema.BURACO.toString());
                        problema.setTipoProblema(TipoProblema.BURACO);
                        break;
                    case 3:
                        tvSelecionarTipoProblema.setText(TipoProblema.FALTA_ILUMINACAO.toString());
                        problema.setTipoProblema(TipoProblema.FALTA_ILUMINACAO);
                        break;
                    case 4:
                        tvSelecionarTipoProblema.setText(TipoProblema.NAO_RECOLHERAM_LIXO.toString());
                        problema.setTipoProblema(TipoProblema.NAO_RECOLHERAM_LIXO);
                        break;
                }
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.tvSelecionarStatusProblema)
    public void selecionarStatusProblema(View view) {
        alertDialog = Utils.onCreateSelectDialog(this, R.string.strTituloStatusProblema, R.array.statusProblemas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //salvar tipo na variavel problema
                switch (i) {
                    case 0:
                        tvSelecionarStatusProblema.setText(TipoStatus.PENDENTE.toString());
                        problema.setTipoStatus(TipoStatus.PENDENTE);
                        break;
                    case 1:
                        tvSelecionarStatusProblema.setText(TipoStatus.REPASSADO.toString());
                        problema.setTipoStatus(TipoStatus.REPASSADO);
                        break;
                    case 2:
                        tvSelecionarStatusProblema.setText(TipoStatus.RESOLVIDO.toString());
                        problema.setTipoStatus(TipoStatus.RESOLVIDO);
                        break;

                }
            }
        });
        alertDialog.show();
    }
}
