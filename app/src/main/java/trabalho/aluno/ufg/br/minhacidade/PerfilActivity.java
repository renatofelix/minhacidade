package trabalho.aluno.ufg.br.minhacidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerfilActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_SELECT = 1;

    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ButterKnife.bind(this);
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

    }

    @OnClick(R.id.btnMeusEnvios)
    public void meusEnvios(View view) {

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
                            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
//                           imageview.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }

        }
    }
}
