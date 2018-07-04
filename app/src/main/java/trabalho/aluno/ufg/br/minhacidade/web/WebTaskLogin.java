package trabalho.aluno.ufg.br.minhacidade.web;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalho.aluno.ufg.br.minhacidade.modelos.Usuario;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

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

        try {
            JSONObject nameAsJSON = new JSONObject(response);
            //nameAsJSON = nameAsJSON.getJSONObject("users");
            JSONArray usuarioJson = nameAsJSON.getJSONArray("users");
            //String login = nameAsJSON.getString("login");
            List<Usuario> usuario = new ArrayList<>();
            usuario = ConvertJsonToUsuario(usuario,usuarioJson);
            EventBus.getDefault().post(usuario);
        } catch (JSONException e) {
            //throw new RuntimeException(e);
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        }
    }

    private List<Usuario> ConvertJsonToUsuario(List<Usuario> user , JSONArray JsonUserArray) throws JSONException {


        for(int i=0; i<JsonUserArray.length(); i++){
            JSONObject json_data = JsonUserArray.getJSONObject(i);
            Usuario usuario = new Usuario();
            usuario.setLogin(json_data.getString("login"));
            usuario.setUsertype(json_data.getString("usertype"));
            usuario.setPassword(json_data.getString("password"));
            usuario.setId(json_data.getString("id"));
            usuario.setNome(json_data.getString("nome"));
            usuario.setPhoto(json_data.getString("photo"));
            usuario.setEmail(json_data.getString("id"));
            usuario.setCpf(json_data.getString("cpf"));
            usuario.setEnviados(json_data.getString("enviados"));
            usuario.setResolvidos(json_data.getString("resolvidos"));


            user.add(usuario);
        }
            return user;


    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.PUT;
    }
}
