package trabalho.aluno.ufg.br.minhacidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoStatus;

import static trabalho.aluno.ufg.br.minhacidade.modelos.TipoStatus.PENDENTE;

public class ProblemaActivity extends AppCompatActivity {

    @BindView(R.id.llRepassado)
    protected LinearLayout llRepassado;

    @BindView(R.id.llFotoDepois)
    protected LinearLayout llFotoDepois;

    @BindView(R.id.tvAntes)
    protected TextView tvAntes;

    @BindView(R.id.tvTipoProblema)
    protected TextView tvTipoProblema;

    @BindView(R.id.tvComentario)
    protected TextView tvComentario;

    @BindView(R.id.tvStatusProblema)
    protected TextView tvStatusProblema;

    @BindView(R.id.ivIconeStatusProblema)
    protected ImageView ivIconeStatusProblema;

    @BindView(R.id.ivFotoAntes)
    protected ImageView ivFotoAntes;

    @BindView(R.id.ivFotoDepois)
    protected ImageView ivFotoDepois;

    Problema problema = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema);

        ButterKnife.bind(this);

        //

        //

        problema = getIntent().getParcelableExtra("problema");

        if (problema != null) {
            initViews(problema);
            initDados(problema);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
//        onBackPressed();
        finish();
        return true;
    }

    private void initDados(Problema problema) {
        //Colcoar as views da tela para receber os dados doobjeto problema
        tvTipoProblema.setText(problema.getTipoProblema().toString());
    }

    private void initViews(Problema problema) {
        switch (problema.getTipoStatus()) {
            case PENDENTE:
                llRepassado.setVisibility(View.GONE);
                llFotoDepois.setVisibility(View.GONE);
                tvAntes.setVisibility(View.GONE);
                ivIconeStatusProblema.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_clock));
                break;
            case REPASSADO:
                llRepassado.setVisibility(View.VISIBLE);
                llFotoDepois.setVisibility(View.GONE);
                tvAntes.setVisibility(View.GONE);
                ivIconeStatusProblema.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_forward));
                break;
            case RESOLVIDO:
                llRepassado.setVisibility(View.VISIBLE);
                llFotoDepois.setVisibility(View.VISIBLE);
                tvAntes.setVisibility(View.VISIBLE);
                ivIconeStatusProblema.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                break;
        }

        tvTipoProblema.setText(problema.getTipoProblema().toString());
        tvComentario.setText(problema.getComentario());

        Picasso.get().load(problema.getLinkImagem()).fit().into(ivFotoAntes);
        if (ivFotoDepois.getVisibility() == View.VISIBLE) {
            Picasso.get().load(problema.getLinkImagem()).fit().into(ivFotoDepois);
        }

        tvStatusProblema.setText("Status do Problema: " + problema.getTipoStatus().toString());
    }

    @OnClick(R.id.ivLocalizacao)
    public void abrirLocalizacao(View view) {

    }
}
