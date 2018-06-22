package trabalho.aluno.ufg.br.minhacidade;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class CadastroProblemaActivity extends AppCompatActivity {

    @BindView(R.id.tvTipoProblema)
    protected TextView tvTipoProblema;

    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_problema);
    }

    @OnClick
    public void selecionarTipoProblema(View view) {
        alertDialog = Utils.onCreateSelectDialog(this, R.string.strTituloTipoProblema, R.array.tiposProblemas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //salvar tipo na variavel problema
            }
        });
        alertDialog.show();
    }
}
