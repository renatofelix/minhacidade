package trabalho.aluno.ufg.br.minhacidade.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;
import trabalho.aluno.ufg.br.minhacidade.web.WebError;
import trabalho.aluno.ufg.br.minhacidade.web.WebTaskLogin;

public class LoginFragment extends Fragment {

    @BindView(R.id.tietEmail)
    protected TextInputEditText tietEmail;

    @BindView(R.id.tietSenha)
    protected TextInputEditText tietSenha;

    @BindView(R.id.tilEmail)
    protected TextInputLayout tilEmail;

    @BindView(R.id.tilSenha)
    protected TextInputLayout tilSenha;

    MaterialDialog dialog;
    SharedPreferences sharedPref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_logar, container, false);
        ButterKnife.bind(this, view);

        setupButtonRememberPassword();

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        return view;
    }

    @OnClick(R.id.btnCadastrar)
    public void btnCadastrar(View view) {
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
        openUrlIntent.setData(
                Uri.parse("http://www.pudim.com.br"));
        startActivity(openUrlIntent);
    }

    @OnClick(R.id.btnLogar)
    public void btnLogar(View view) {
        tryLogin();
    }

    private void setupButtonRememberPassword() {
    }

    private void tryLogin() {
        if(!"".equals(tietEmail.getText().toString())){
            showLoading();
            sendCredentials(tietEmail.getText().toString(),
                    tietSenha.getText().toString());
        }else{
            tilEmail.setError("Preencha o campo email");
        }

    }

    private void sendCredentials(String email, String pass) {
        WebTaskLogin taskLogin = new WebTaskLogin(getContext(),
                email, pass);
        taskLogin.execute();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(getContext())
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    @Subscribe
    public void onEvent(List<Usuario> response){
        hideLoading();
//        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
//        openUrlIntent.setData(
//                Uri.parse("http://www.freescreencleaner.com/"));
//        startActivity(openUrlIntent);

        //TODO: verificar login e senha
        if(VerificarUsuario(response))
        {



        //TODO: salvar no sharedpreferences se o usuario esta logado, ou todas as informacoes do usuario

        //TODO: se logar com sucesso chamar essas duas linhas, para alterar a fragment que apresenta na tela
        ((MainActivity)getActivity()).usuarioLogado = true;
        ((MainActivity)getActivity()).mudarParaMeusEnviados();
        ((MainActivity)getActivity()).initFab();
        }
    }

    private boolean VerificarUsuario(List<Usuario> response)
    {
        String email = tietEmail.getText().toString();
        String senha = tietSenha.getText().toString();
        Usuario usuarioRef;

        List<Usuario> user = response;

        for (Usuario usuarios: user)
        {
            if(usuarios.getLogin().equals(email) && usuarios.getPassword().equals(senha)) {

                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString(getString(R.string.email), email);
                editor.putString(getString(R.string.senha), senha);
                editor.putString(getString(R.string.id), usuarios.getId());
                editor.putString(getString(R.string.usertype), usuarios.getUsertype());
                editor.putString("Nome", usuarios.getNome());
                editor.putString("Photo", usuarios.getPhoto());
                editor.putString("Email",usuarios.getEmail());
                editor.putString("CPF",usuarios.getCpf());
                editor.putString("Enviados",usuarios.getEnviados());
                editor.putString("Resolvidos", usuarios.getResolvidos());

                editor.apply();

                return true;
            }
        }

        Snackbar.make(tietEmail, "Login ou senha incorretos", Snackbar.LENGTH_LONG).show();

                return false;
    }



    @Subscribe
    public void onEvent(WebError error){
        hideLoading();
        Snackbar.make(tietEmail,
                error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
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

    //TODO: de uma olhada nesse método
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
