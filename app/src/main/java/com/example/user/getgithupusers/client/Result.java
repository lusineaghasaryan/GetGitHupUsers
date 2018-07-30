package com.example.user.getgithupusers.client;

import java.util.List;

public class Result {
    public static class UsersList {
        public List<User> items;
    }
    public class User{
        public long id;
        public String name;
        public String avatar_url;
        public String login;
    }
}
