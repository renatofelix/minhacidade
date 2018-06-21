package trabalho.aluno.ufg.br.minhacidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
            initViews(problema.getTipoStatus());
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

    private void initViews(TipoStatus tipoStatus) {
        switch (tipoStatus) {
            case PENDENTE:
                llRepassado.setVisibility(View.GONE);
                llFotoDepois.setVisibility(View.GONE);
                tvAntes.setVisibility(View.GONE);
                break;
            case REPASSADO:
                llRepassado.setVisibility(View.VISIBLE);
                llFotoDepois.setVisibility(View.GONE);
                tvAntes.setVisibility(View.GONE);
                break;
            case RESOLVIDO:
                llRepassado.setVisibility(View.VISIBLE);
                llFotoDepois.setVisibility(View.VISIBLE);
                tvAntes.setVisibility(View.VISIBLE);
                break;
        }
    }
}
