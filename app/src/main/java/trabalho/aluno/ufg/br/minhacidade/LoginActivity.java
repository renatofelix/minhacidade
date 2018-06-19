package trabalho.aluno.ufg.br.minhacidade;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tietEmail)
    protected TextInputEditText tietEmail;

    @BindView(R.id.tietSenha)
    protected TextInputEditText tietSenha;

    @BindView(R.id.tilEmail)
    protected TextInputLayout tilEmail;

    @BindView(R.id.tilSenha)
    protected TextInputLayout tilSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);


    }

    @OnClick(R.id.btnLogar)
    public void logar (View view) {
        //erro email ou senha invalidos
//        //erro email invalido
//        tilEmail.setErrorEnabled(true);
//        tilEmail.setError("Email não encontrado.");
//
//        //erro senha invalida
//        tilSenha.setErrorEnabled(true);
//        tilSenha.setError("Senha inválida.");

        //erro conexao
//        Utils.createSnackbar(tilEmail, "Erro na conexão com o servidor.");

        //Logado com Sucesso
//        Utils.createSnackbar(tilEmail, "Logado com Sucesso.");

        //dialog logando

    }
}
