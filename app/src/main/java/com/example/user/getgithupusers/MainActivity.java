package com.example.user.getgithupusers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.getgithupusers.client.GitHubService;
import com.example.user.getgithupusers.client.Result;
import com.example.user.getgithupusers.model.GitUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<Result.UsersList> usersCall = gitHubService.usersList();
        usersCall.enqueue(new Callback<Result.UsersList>() {
            @Override
            public void onResponse(Call<Result.UsersList> call, Response<Result.UsersList> response) {

                if(response.isSuccessful()) {
                    List<Result.User> users = response.body().items;
                    List<GitUser> gUsers = new ArrayList<>();
                    for (Result.User user : users) {
                        GitUser gUser = new GitUser();
                        gUser.setmId(user.id);
                        gUser.setmName(user.name);
                        gUser.setmAvatarUrl(user.avatar_url);
                        gUser.setmUsername(user.login);
                        Log.v("LLLL", "OOOO = " + gUser.getmUsername());
                        gUsers.add(gUser);

                    }
                }else Log.v("LLLL", "elseeee = " + response.code());

            }

            @Override
            public void onFailure(Call<Result.UsersList> call, Throwable t) {
Log.v("KKKKK", "PPPP = " + t);
            }
        });

    }

}
