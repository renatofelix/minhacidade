package trabalho.aluno.ufg.br.minhacidade.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;

public class WebTaskLogin extends WebTaskBase {

    private static String URL = "login";
    private String FIELD_EMAIL = "email";
    private String FIELD_PASSWORD = "password";

    private String email;
    private String password;

    public WebTaskLogin(Context context, String email, String password) {
        super(context, URL);
        this.email = email;
        this.password = password;
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_EMAIL, email );
        requestMap.put(FIELD_PASSWORD, password );

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    @Override
    void handleResponse(String response) {

        //Se quiser fazer leitura automatica, busque usar a biblioteca GSON DE ANDROID


//        try {
//            JSONObject requestAsJSON = new JSONObject(response);
//            String status = requestAsJSON.getString("status");
//            JSONArray reclamacoesAsJSON = requestAsJSON.getJSONArray("reclamacoes");
//
//            for(int index = 0; index < reclamacoesAsJSON.length(); index++){
//                reclamacaoAsJSON = reclamacoesAsJSON.getJSONObject(index);
//                String ideReclamacao = reclamacaoAsJSON.getString("id_reclamacao");
//
//
//
//            }
//
//
//        } catch (JSONException e) {
//            EventBus.getDefault().post(
//                    new WebError(
//                            "Resposta inválida do servidor", URL));
//        }


        try {
            JSONObject nameAsJSON = new JSONObject(response);
            String login = nameAsJSON.getString("login");
            List<Usuario> usuario = (List<Usuario>) nameAsJSON.getJSONObject("users");
            EventBus.getDefault().post(usuario);
        } catch (JSONException e) {
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.PUT;
    }
}
