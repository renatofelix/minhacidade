package trabalho.aluno.ufg.br.minhacidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoStatus;

import static trabalho.aluno.ufg.br.minhacidade.modelos.TipoStatus.PENDENTE;

public class ProblemaActivity extends AppCompatActivity {

    @BindView(R.id.llRepassado)
    protected LinearLayout llRepassado;

    @BindView(R.id.llFotoDepois)
    protected LinearLayout llFotoDepois;

    @BindView(R.id.tvAntes)
    protected TextView tvAntes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema);

        initViews(PENDENTE);
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
