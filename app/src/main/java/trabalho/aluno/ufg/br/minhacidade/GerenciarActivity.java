package trabalho.aluno.ufg.br.minhacidade;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class GerenciarActivity extends AppCompatActivity {

    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar);
        ButterKnife.bind(this);

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
}
