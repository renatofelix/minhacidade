package trabalho.aluno.ufg.br.minhacidade;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class CadastroProblemaActivity extends AppCompatActivity {

    @BindView(R.id.tvTipoProblema)
    protected TextView tvTipoProblema;

    @BindView(R.id.etDescricaoProblema)
    protected EditText etDescricaoProblema;

    @BindView(R.id.ivFoto)
    protected ImageView ivFoto;

    private Dialog alertDialog;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_SELECT = 2;

    private Problema problema = new Problema();
    private String[] tiposProblemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_problema);

        ButterKnife.bind(this);

        tiposProblemas = getResources().getStringArray(R.array.tiposProblemas);
    }

    @OnClick(R.id.tvTipoProblema)
    public void selecionarTipoProblema(View view) {
        alertDialog = Utils.onCreateSelectDialog(this, R.string.strTituloTipoProblema, R.array.tiposProblemas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //salvar tipo na variavel problema
                switch (i) {
                    case 0:
                        problema.setTipoProblema(TipoProblema.FALTA_SINALIZACAO);
                        break;
                    case 1:
                        problema.setTipoProblema(TipoProblema.ENTULHO_TERRENO_BALDIO);
                        break;
                    case 2:
                        problema.setTipoProblema(TipoProblema.BURACO);
                        break;
                    case 3:
                        problema.setTipoProblema(TipoProblema.FALTA_ILUMINACAO);
                        break;
                    case 4:
                        problema.setTipoProblema(TipoProblema.NAO_RECOLHERAM_LIXO);
                        break;

                }
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.ivFoto)
    public void selecionarFoto(View view) {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, 10);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
////            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            startActivityForResult(photoPickerIntent, 10);
//        }
        showPictureDialog();

    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Selecione ação:");
        String[] pictureDialogItems = {
                "Selecionar foto da Galeria",
                "Tirar uma foto" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, REQUEST_IMAGE_SELECT);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @OnClick(R.id.btnRelatar)
    public void salvarProblema(View view) {

        problema.setDescricao(etDescricaoProblema.getText().toString());

        //Se todos os campos estiverem preenchidos e colocados no objeto problema, fazer chamada para salvar o objeto
    }

    @Override
    public boolean onSupportNavigateUp() {
//        onBackPressed();
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
           switch (requestCode) {
               case REQUEST_IMAGE_CAPTURE:
                   Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//                   imageview.setImageBitmap(thumbnail);
//                   saveImage(thumbnail);
                   Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();

                   break;
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
