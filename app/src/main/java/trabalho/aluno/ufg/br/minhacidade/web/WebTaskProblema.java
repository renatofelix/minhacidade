package trabalho.aluno.ufg.br.minhacidade.web;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoProblema;
import trabalho.aluno.ufg.br.minhacidade.modelos.TipoStatus;

public class WebTaskProblema extends WebTaskBase {

    private static String URL = "problema";

    private static final String TAG ="WebTaskProblema";

    private String FIELD_ID = "id";
    private String FIELD_TYPE_PROBLEM = "typeproblem";
    private String FIELD_IMG = "img";
    private String FIELD_DESCRICAO = "descricao";
    private String FIELD_RESPONSAVEL = "responsavel";
    private String FIELD_STATUS = "status";
    private String FIELD_COMMENT = "comentario";
    private String FIELD_PHOTO = "fotoresponsavel";
    private String FIELD_LOCATION = "localizacao";
    private String FIELD_ADRESS = "endereco";
    private String FIELD_DATA = "data";




    private String id;
    private String typeproblem;
    private String img;
    private String descricao;
    private String resposavel;
    private String status;
    private String comment;
    private String photo;
    private String location;
    private String data;


    public WebTaskProblema(Context context) {
        super(context, URL);
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
//        requestMap.put(FIELD_ID, id );
//        requestMap.put(FIELD_TYPE_PROBLEM, typeproblem );
//        requestMap.put(FIELD_IMG,img);
//        requestMap.put(FIELD_DESCRICAO , descricao);
//        requestMap.put(FIELD_RESPONSAVEL, resposavel);
//        requestMap.put(FIELD_STATUS, status);
//        requestMap.put(FIELD_COMMENT, comment);
//        requestMap.put(FIELD_PHOTO, photo);
//        requestMap.put(FIELD_LOCATION, location);
//        requestMap.put(FIELD_DATA, data);

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    @Override
    void handleResponse(String response) {

        try {
            JSONObject nameAsJSON = new JSONObject(response);
            //nameAsJSON = nameAsJSON.getJSONObject("users");
            JSONArray problemaJSON = nameAsJSON.getJSONArray("problemas");
            //String login = nameAsJSON.getString("login");
            List<Problema> problema = new ArrayList<>();
            problema = ConvertJsonToProblema(problema,problemaJSON);
            EventBus.getDefault().post(problema);
        } catch (JSONException e) {
            //throw new RuntimeException(e);
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    private List<Problema> ConvertJsonToProblema(List<Problema> problemas , JSONArray JsonUserArray) throws JSONException, ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0; i<JsonUserArray.length(); i++){
            JSONObject json_data = JsonUserArray.getJSONObject(i);
            Problema problema = new Problema();
            problema.setId(json_data.getString("id"));
            problema.setTipoProblema(TipoProblema.valueOf(json_data.getString("typeproblem")));
            problema.setLinkImagem(json_data.getString("img"));
            problema.setDescricao(json_data.getString("descricao"));
            problema.setResponsavel(json_data.getString("responsavel"));
            problema.setTipoStatus(TipoStatus.valueOf(json_data.getString("status")));
            problema.setComentario(json_data.getString("status"));
            problema.setComentario(json_data.getString("comentario"));
            problema.setFotoresponsavel(json_data.getString("fotoresponsavel"));
            problema.setLocalizacao(json_data.getString("localizacao"));
            problema.setEndereco(json_data.getString("endereco"));
            problema.setData(formato.parse(json_data.getString("data")));

            problemas.add(problema);
        }
        return problemas;

        //usuario.setLogin(JsonUserArray.getString(0).toString());

    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.PUT;
    }
}
