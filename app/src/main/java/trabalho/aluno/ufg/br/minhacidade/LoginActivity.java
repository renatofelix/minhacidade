package trabalho.aluno.ufg.br.minhacidade;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.Subscribe;

import trabalho.aluno.ufg.br.minhacidade.utils.Utils;
import trabalho.aluno.ufg.br.minhacidade.web.WebError;
import trabalho.aluno.ufg.br.minhacidade.web.WebTaskLogin;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tietEmail)
    protected TextInputEditText tietEmail;

    @BindView(R.id.tietSenha)
    protected TextInputEditText tietSenha;

    @BindView(R.id.tilEmail)
    protected TextInputLayout tilEmail;

    @BindView(R.id.tilSenha)
    protected TextInputLayout tilSenha;

    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        /*
        ImageView buttonClose = findViewById(R.id.button_close);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/
        setupButtonRememberPassword();
        setupButtonRegister();
        setupLogin();
    }

    private void setupButtonRegister() {

        Button buttonRegister =
                findViewById(R.id.btnCadastrar);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
                openUrlIntent.setData(
                        Uri.parse("http://www.pudim.com.br"));
                startActivity(openUrlIntent);
            }
        });

    }

    private void setupButtonRememberPassword() {
    }

    private void setupLogin() {
        Button buttonLogin =
                findViewById(R.id.btnLogar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });
    }

    private void tryLogin() {
        EditText editTextEmail = findViewById(R.id.tietEmail);
        EditText editTextPassword = findViewById(R.id.tietSenha);

        if(!"".equals(editTextEmail.getText().toString())){
            showLoading();
            sendCredentials(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
        }else{
            editTextEmail.setError("Preencha o campo email");
        }

    }

    private void sendCredentials(String email, String pass) {
        WebTaskLogin taskLogin = new WebTaskLogin(this,
                email, pass);
        taskLogin.execute();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(this)
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    @Subscribe
    public void onEvent(String response){
        hideLoading();
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
        openUrlIntent.setData(
                Uri.parse("http://www.freescreencleaner.com/"));
        startActivity(openUrlIntent);
    }

    @Subscribe
    public void onEvent(WebError error){
        hideLoading();
        Snackbar.make(findViewById(R.id.container),
                error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
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
