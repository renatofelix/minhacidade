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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.utils.Utils;

public class CadastroProblemaActivity extends AppCompatActivity {

    @BindView(R.id.tvTipoProblema)
    protected TextView tvTipoProblema;

    @BindView(R.id.tvPlace)
    protected TextView tvPlace;

    @BindView(R.id.etDescricaoProblema)
    protected EditText etDescricaoProblema;

    @BindView(R.id.ivFoto)
    protected ImageView ivFoto;

    private Dialog alertDialog;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_SELECT = 2;
    static final int REQUEST_PLACE_PICKER = 3;

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
        selecionarFonteDaFotoDialogo();
    }

    @OnClick({R.id.rlLocalizacao, R.id.ivPlace, R.id.tvPlace})
    public void selecionarLocal(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), REQUEST_PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private void selecionarFonteDaFotoDialogo(){
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
                                pegarFotoDaGaleria();
                                break;
                            case 1:
                                pegarFotoDaCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void pegarFotoDaGaleria() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, REQUEST_IMAGE_SELECT);
    }

    private void pegarFotoDaCamera() {
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
               case REQUEST_PLACE_PICKER:
                   Place place = PlacePicker.getPlace(data, this);
                   String toastMsg = String.format("%s", place.getLatLng());
                   Log.d("lag", String.valueOf(place.getLatLng()));
//                   Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                   tvPlace.setText(place.getAddress());
                   break;
           }

        }
    }
}
