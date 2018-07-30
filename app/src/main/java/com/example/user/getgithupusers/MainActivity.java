package com.example.user.getgithupusers;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.user.getgithupusers.adapter.GUsersAdapter;
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

    public RecyclerView mUsersListRV;
    public GUsersAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    public Context mContext;
    public  GitHubService mGitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mUsersListRV = findViewById(R.id.users_list_recycler_view);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mUsersListRV.setLayoutManager(mLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGitHubService = retrofit.create(GitHubService.class);

        final List<GitUser> mGitUsersList = new ArrayList<>();

        final Call<List<Result.User>> usersCall = mGitHubService.usersList();
        usersCall.enqueue(new Callback<List<Result.User>>() {
            @Override
            public void onResponse(Call<List<Result.User>> call, Response<List<Result.User>> response) {
                List<Result.User> usersList =  new ArrayList<>();
                usersList.addAll(response.body());

                for (Result.User user : usersList) {
                    GitUser gUser = new GitUser();
                    gUser.setmId(user.id);
                    gUser.setmName(user.name);
                    gUser.setmAvatarUrl(user.avatar_url);
                    gUser.setmUsername(user.login);

                    mGitUsersList.add(gUser);
                }

                mAdapter = new GUsersAdapter(mGitUsersList, mContext);
                mUsersListRV.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Result.User>> call, Throwable t) {

            }
        });


    }



    private void searchUser(final String username){
        Call<Result.User> mUser = mGitHubService.searchUser(username);
        mUser.enqueue(new Callback<Result.User>() {
            @Override
            public void onResponse(Call<Result.User> call, Response<Result.User> response) {
                Result.User mSerchUser = response.body();
                GitUser gitUser = new GitUser();

                gitUser.setmUsername(mSerchUser.login);
                gitUser.setmAvatarUrl(mSerchUser.avatar_url);
                gitUser.setmName(mSerchUser.name);
                gitUser.setmId(mSerchUser.id);

                Toast.makeText(mContext, gitUser.getmUsername() + "  " + gitUser.getmId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Result.User> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        item.expandActionView();
        final SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    searchUser(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }


}
