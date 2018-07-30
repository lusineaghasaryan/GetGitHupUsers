package com.example.user.getgithupusers.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.getgithupusers.R;

public class GUsersListViewHolder extends RecyclerView.ViewHolder {
    public TextView mUsernameTextView;
    public ImageView mAvatarImageView;

    public GUsersListViewHolder(View itemView) {
        super(itemView);
        mUsernameTextView = itemView.findViewById(R.id.username_text_view);
        //mAvatarImageView = itemView.findViewById(R.id.avatar_image_view);
    }
}
