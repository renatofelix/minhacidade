package trabalho.aluno.ufg.br.minhacidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.main.MainActivity;
import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_SELECT = 1;

    private boolean editMode = false;

    Usuario usuario = null;

    @BindView(R.id.tietNome)
    protected TextInputEditText tietNome;

    @BindView(R.id.tietCPF)
    protected TextInputEditText tietCPF;

    @BindView(R.id.tietEmail)
    protected TextInputEditText tietEmail;

    @BindView(R.id.tvEnviados)
    protected TextView tvEnviados;

    @BindView(R.id.tvResolvidos)
    protected TextView tvResolvidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ButterKnife.bind(this);


        //Todo: pegar o usuario igual pegar problema na tela problemaactivity
        usuario = getIntent().getParcelableExtra("usuario");

        tietNome.setText(usuario.getNome().toString());
        tietEmail.setText(usuario.getEmail().toString());
        tietCPF.setText(usuario.getCpf());
        tvEnviados.setText("Enviados: "+usuario.getEnviados());
        tvResolvidos.setText("Resolvidos: "+usuario.getResolvidos());

        initViews(usuario);
    }

    private void initViews(Usuario usuario) {
        tietNome.setText(usuario.getNome().toString());
        tietEmail.setText(usuario.getEmail().toString());
        tietCPF.setText(usuario.getCpf());
        tvEnviados.setText("Enviados: "+usuario.getEnviados());
        tvResolvidos.setText("Resolvidos: "+usuario.getResolvidos());

        tietNome.setEnabled(false);
        tietEmail.setEnabled(false);
        tietCPF.setEnabled(false);

//        tietNome.setInputType(InputType.TYPE_NULL);
//        tietEmail.setInputType(InputType.TYPE_NULL);
//        tietCPF.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public boolean onSupportNavigateUp() {
//        onBackPressed();
        finish();
        return true;
    }



    @OnClick(R.id.ivFoto)
    public void selecionarFoto(View view) {
        if (editMode) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, REQUEST_IMAGE_SELECT);
        }
    }

    @OnClick(R.id.tvDeslogar)
    public void deslogar(View view) {
        usuario = null;

        usuario.setId("");
        usuario.setNome("");
        usuario.setCpf("");
        usuario.setEmail("");
        usuario.setPassword("");
    }

    @OnClick(R.id.ivEdit)
    public void EditPerfil()
    {

        editMode = true;

        tietNome.setEnabled(true);
        tietEmail.setEnabled(true);
        tietCPF.setEnabled(true);

//        tietNome.setInputType(InputType.TYPE_NULL);
//        tietEmail.setInputType(InputType.TYPE_NULL);
//        tietCPF.setInputType(InputType.TYPE_NULL);
    }


    @OnClick(R.id.btnSalvar)
    public void salvar(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_SELECT:
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                           String path = saveImage(bitmap);
//                           imageview.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        }
    }
}
