package com.example.user.getgithupusers.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("/users")
    Call<Result.UsersList> usersList();

    @GET("/users/{user}")
    Call<Result.User> searchUser(@Path("user") String user);

}
