package com.example.user.getgithupusers.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.getgithupusers.R;
import com.example.user.getgithupusers.model.GitUser;
import com.example.user.getgithupusers.viewHolder.GUsersListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GUsersAdapter extends RecyclerView.Adapter<GUsersListViewHolder> {

    List<GitUser> mGitUsersList;
    Context mContext;

    public GUsersAdapter(List<GitUser> gitUsersList, Context context) {
        mGitUsersList = new ArrayList<>();
        mGitUsersList.addAll(gitUsersList);
        mContext = context;
    }

    @NonNull
    @Override
    public GUsersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GUsersListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GUsersListViewHolder holder, int position) {
        GitUser gitUser = mGitUsersList.get(position);
        holder.mUsernameTextView.setText(gitUser.getmUsername());

    }

    @Override
    public int getItemCount() {
        return mGitUsersList.size();
    }
}
