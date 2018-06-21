package trabalho.aluno.ufg.br.minhacidade;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class CadastrarActivity extends AppCompatActivity {


    @BindView(R.id.tietNome)
    protected TextInputEditText tietNome;

    @BindView(R.id.tilNome)
    protected TextInputLayout tilNome;

    @BindView(R.id.tietCPF)
    protected TextInputEditText tietCPF;

    @BindView(R.id.tilCPF)
    protected TextInputLayout tilCPF;

    @BindView(R.id.tietEmail)
    protected TextInputEditText tietEmail;

    @BindView(R.id.tilEmail)
    protected TextInputLayout tilEmail;

    @BindView(R.id.tietConfirmacaoEmail)
    protected TextInputEditText tietConfirmacaoEmail;

    @BindView(R.id.tilConfirmacaoEmail)
    protected TextInputLayout tilConfirmacaoEmail;

    @BindView(R.id.tietSenha)
    protected TextInputEditText tietSenha;

    @BindView(R.id.tilSenha)
    protected TextInputLayout tilSenha;

    @BindView(R.id.tietConfirmacaoSenha)
    protected TextInputEditText tietConfirmacaoSenha;

    @BindView(R.id.tilConfirmacaoSenha)
    protected TextInputLayout tilConfirmacaoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
    }

    @OnClick(R.id.btnCadastrarGoogle)
    public void cadastrarGoogle(View view) {

    }

    @OnClick(R.id.btnCadastrar)
    public void cadastrar(View view) {
        //usuario ja existe
//        tilEmail.setErrorEnabled(true);
//        tilEmail.setError("Email já existe.");

        //erro emails diferentes
//        tilConfirmacaoEmail.setErrorEnabled(true);
//        tilConfirmacaoEmail.setError("Emails não coincidem");

        //erro senhas diferentes
//        tilConfirmacaoSenha.setErrorEnabled(true);
//        tilConfirmacaoSenha.setError("Senhas não coincidem");

        //Cadastrado com sucesso
//        Utils.createSnackbar(tilEmail, "Cadastrado com Sucesso.");

    }
}
