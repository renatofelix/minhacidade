package trabalho.aluno.ufg.br.minhacidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;

public class MeusEnviosActivity extends AppCompatActivity {

    @BindView(R.id.rvMeusEnvios)
    protected RecyclerView rvMeusEnvios;

    ArrayList<Problema> problemas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_envios);
        
        initRecyclerView(rvMeusEnvios);
    }

    private void initRecyclerView(RecyclerView rvMeusEnvios) {
    }
}
