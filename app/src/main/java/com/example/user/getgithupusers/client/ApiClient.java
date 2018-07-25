package com.example.user.getgithupusers.client;

import android.util.Log;

import com.example.user.getgithupusers.model.GitUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private static final String USERS = "/users";
    private static final String USER_BY_USERNAME = "/%s";

    public static GitUser getGitUser(String username) {
        GitUser gitUser = new GitUser();

        URL url = null;
        try {
            url = new URL(BASE_URL + USERS);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
            JSONObject userJSON = readStream(in);
            gitUser = GitUser.createFromJSON(userJSON);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gitUser;
    }

    private static JSONObject readStream(InputStream is) throws IOException, JSONException {
        JSONObject jsonObject = null;

        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }

        jsonObject = new JSONObject(total.toString());

        Log.v("ApiClient", total.toString());


        return jsonObject;
    }
}
