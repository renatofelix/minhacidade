package trabalho.aluno.ufg.br.minhacidade.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by johnn on 19/06/2018.
 */

public class Utils {

    public static void createSnackbar(View view, String mensagem){
        Snackbar snackbar = Snackbar.make(view ,mensagem, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
