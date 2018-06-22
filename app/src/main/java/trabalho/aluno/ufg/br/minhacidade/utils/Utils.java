package trabalho.aluno.ufg.br.minhacidade.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import trabalho.aluno.ufg.br.minhacidade.R;

/**
 * Created by johnn on 19/06/2018.
 */

public class Utils {

    public static void createSnackbar(View view, String mensagem){
        Snackbar snackbar = Snackbar.make(view ,mensagem, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static Dialog onCreateSelectDialog(Activity activity, int idTitulo, int idArray, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(idTitulo)
                .setItems(idArray, onClickListener);
        return builder.create();
    }
}
