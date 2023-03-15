package com.example.bum4dot1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MastodonController {

    @FXML
    private CheckBox boostedCheck;

    @FXML
    private Button nextButton;

    @FXML
    private TextField originalAuthor;

    @FXML
    private Button previousButton;

    @FXML
    private TextField publicationDate;

    @FXML
    private WebView webView;

    @FXML
    void nextAction(ActionEvent event) {
        if (currentToot < lista.size()-1) {
            getTootInfo(currentToot + 1);
            currentToot = currentToot +1;
        }
    }

    @FXML
    void previousAction(ActionEvent event) {
        if (currentToot > 0){
            getTootInfo(currentToot - 1);
            currentToot = currentToot - 1;
        }

    }

    List<Status> lista;

    public static String request(String endpoint) {
        String result ="";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://mastodon.social/api/v1/" + endpoint).get().addHeader("Authorization", "Bearer " + System.getenv("TOKEN"))
                .build();

        try  {

            Response response = client.newCall(request).execute();
            if(response.code()==200){
                result= response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Status> converter(){
        String id= "109903169358413683";
        String body=  request("accounts/" + id + "/statuses");
        System.out.println(body);
        Gson gson = new Gson();
        JsonArray jsonArray= gson.fromJson(body, JsonArray.class);
        Type statusList = new TypeToken<ArrayList<Status>>(){}.getType();
        List<Status> list= gson.fromJson(jsonArray.getAsJsonArray(), statusList);
        return list;
    }
    int currentToot;
    public void initialize() {
        lista = converter();
        currentToot = 0;
        getTootInfo(currentToot);
    }

    public void getTootInfo(int i) {

        if (i >= 0) {
            Status individualToot = lista.get(i);

            if (individualToot.reblog == null) {

                originalAuthor.setText(individualToot.account.display_name);
                publicationDate.setText(individualToot.created_at);
                webView.getEngine().loadContent(individualToot.content);
                boostedCheck.setSelected(false);
            } else {
                originalAuthor.setText(individualToot.reblog.account.display_name);
                publicationDate.setText(individualToot.reblog.created_at);
                webView.getEngine().loadContent(individualToot.reblog.content);
                boostedCheck.setSelected(true);
            }
        }
    }


}