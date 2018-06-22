package trabalho.aluno.ufg.br.minhacidade.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import trabalho.aluno.ufg.br.minhacidade.CadastroProblemaActivity;
import trabalho.aluno.ufg.br.minhacidade.GerenciarActivity;
import trabalho.aluno.ufg.br.minhacidade.PerfilActivity;
import trabalho.aluno.ufg.br.minhacidade.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    protected BottomNavigationView bottomNavigationView;

    @BindView(R.id.fabAdicionarProblema)
    FloatingActionButton fabAdicionarProblema;

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initBottomNavigationView();
        initFab();
    }

    private void initFab() {
        fabAdicionarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroProblemaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.gerenciar:
                Toast.makeText(getApplicationContext(),"Gerenciar",Toast.LENGTH_LONG).show();
                intent = new Intent(this, GerenciarActivity.class);
                intent.putExtra("ADM", true);

                startActivity(intent);

//                startActivity(new Intent(this, ));

                return true;
            case R.id.perfil:
                Toast.makeText(getApplicationContext(),"Perfil",Toast.LENGTH_LONG).show();

                intent = new Intent(this, PerfilActivity.class);
//                intent.putExtra("usuario", usuario);

                startActivity(intent);
                return true;
            case R.id.sair:
                Toast.makeText(getApplicationContext(),"Sair", Toast.LENGTH_LONG).show();

                //TODO:LOGOUT
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initBottomNavigationView() {
        fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.enviados:
                        fragment = new MeusFragment();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_container, fragment).commit();
                        break;
                    case R.id.novos:
                        fragment = new NovosFragment();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_container, fragment).commit();
                        break;
                }
                return true;
            }
        });

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, new NovosFragment()).commit();
    }
}
