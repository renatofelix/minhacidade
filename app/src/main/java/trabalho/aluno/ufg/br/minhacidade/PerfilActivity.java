package trabalho.aluno.ufg.br.minhacidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }

    @OnClick(R.id.tvDeslogar)
    public void deslogar(View view) {

    }

    @OnClick(R.id.btnMeusEnvios)
    public void meusEnvios(View view) {

    }

    @OnClick(R.id.btnSalvar)
    public void salvar(View view) {

    }
}
