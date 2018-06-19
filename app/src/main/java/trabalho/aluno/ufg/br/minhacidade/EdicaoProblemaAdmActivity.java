package trabalho.aluno.ufg.br.minhacidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;

public class EdicaoProblemaAdmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_problema_adm);
    }

    @OnClick(R.id.btnResponder)
    public void responder(View view) {

    }
}
