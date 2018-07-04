package trabalho.aluno.ufg.br.minhacidade.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import trabalho.aluno.ufg.br.minhacidade.modelos.User;
import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    protected BottomNavigationView bottomNavigationView;

    @BindView(R.id.fabAdicionarProblema)
    FloatingActionButton fabAdicionarProblema;

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    public boolean usuarioLogado = false;
//    private User usuario;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        verificaUsuarioLogado();
//
//        initBottomNavigationView();
//        initFab();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initFab();
        initBottomNavigationView();
    }

    private void verificaUsuarioLogado() {
        //TODO: verificar no sharedpreferences se a variavel de usuaro logado e o id existe, se sim ele esta logado
        //TODO: se tiver logado colocar as informações no objeto usuario

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);



        String id = sharedPref.getString(getString(R.string.id),null);

        if(id!=null)
        {
            String senha = sharedPref.getString(getString(R.string.senha),null);
            String login = sharedPref.getString(getString(R.string.email),null);
            String userType = sharedPref.getString(getString(R.string.usertype),null);

            usuario.setLogin(login);
            usuario.setId(id);
            usuario.setUsertype(userType);
            usuario.setPassword(senha);

            usuarioLogado = true;

        }

    }

    private void initFab() {
        if (!usuarioLogado) {
            fabAdicionarProblema.hide();
        } else {
            fabAdicionarProblema.show();
        }

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

        if (!usuarioLogado) {
            MenuItem item = menu.findItem(R.id.perfil);
            item.setVisible(false);
            MenuItem item2 = menu.findItem(R.id.sair);
            item2.setVisible(false);
            MenuItem item3 = menu.findItem(R.id.gerenciar);
            item3.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.gerenciar:
                Toast.makeText(getApplicationContext(), "Gerenciar", Toast.LENGTH_LONG).show();
                intent = new Intent(this, GerenciarActivity.class);
                intent.putExtra("ADM", true);

                startActivity(intent);

//                startActivity(new Intent(this, ));

                return true;
            case R.id.perfil:
                Toast.makeText(getApplicationContext(), "Perfil", Toast.LENGTH_LONG).show();

                intent = new Intent(this, PerfilActivity.class);
//                intent.putExtra("usuario", usuario);
                startActivity(intent);
                return true;
            case R.id.sair:
                Toast.makeText(getApplicationContext(), "Sair", Toast.LENGTH_LONG).show();

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
                switch (id) {
                    case R.id.enviados:
                        fragment = new LoginFragment();
                        item.setTitle(getResources().getString(R.string.menu_item_logar));
                        if (usuarioLogado) {
                            item.setTitle(getResources().getString(R.string.menu_item_meus_enviados));
                            fragment = new MeusFragment();
                        }
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

    public void mudarParaMeusEnviados() {
        MenuItem item = bottomNavigationView.getMenu().findItem(R.id.enviados);
        item.setTitle(getResources().getString(R.string.menu_item_meus_enviados));

        fragment = new MeusFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
    }
}
