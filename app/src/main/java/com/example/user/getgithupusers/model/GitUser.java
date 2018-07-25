package com.example.user.getgithupusers.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GitUser {
    private long mId;
    private String mName;
    private String mUsername;
    private String mAvatarUrl;

    public GitUser(){

    }

    public GitUser(long mId, String mName, String mUsername, String mAvatarUrl) {
        this.mId = mId;
        this.mName = mName;
        this.mUsername = mUsername;
        this.mAvatarUrl = mAvatarUrl;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmAvatarUrl() {
        return mAvatarUrl;
    }

    public void setmAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public static GitUser createFromJSON(JSONObject jsonObject) throws JSONException {
        long id = jsonObject.getLong("id");
        String name = jsonObject.getString("name");
        String avatarUrl = jsonObject.getString("avatar_url");
        String username = jsonObject.getString("login");

        return new GitUser(id, name, username, avatarUrl);

    }
}
