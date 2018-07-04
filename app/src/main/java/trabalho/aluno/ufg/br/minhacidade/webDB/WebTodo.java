package trabalho.aluno.ufg.br.minhacidade.webDB;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import trabalho.aluno.ufg.br.minhacidade.modelos.Task;
import java.util.Map;
import java.util.List;

import okhttp3.Response;

public class WebTodo extends WebConnection{

    private static final String SERVICE = "todos";
    private String token;

    public WebTodo(String token) {
        super(SERVICE);
        this.token = token;
    }

    @Override
    String getRequestContent() {
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("token", token);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return  jsonString;
    }

    @Override
    void handleResponse(Response response) {
        String responseBody = null;
        List<Task> tasks = new LinkedList<>();
        try {
            responseBody = response.body().string();

            JSONArray jsonArray = new JSONArray(responseBody);
            for(int i=0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Task task = new Task();
                task.setDescription(jsonObject.getString("description"));
                task.setName(jsonObject.getString("name"));
                tasks.add(task);
            }
            EventBus.getDefault().post(tasks);
        } catch (IOException e) {
            EventBus.getDefault().post(e);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Exception("A resposta do servidor não é válida"));
        }

    }

}
