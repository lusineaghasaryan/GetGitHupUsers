package com.example.user.getgithupusers.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("/users")
    Call<List<Result.User>> usersList();

    @GET("/users/{user}")
    Call<Result.User> searchUser(@Path("user") String user);

}
