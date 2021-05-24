package com.ilutoo.joyfulchatroom.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.ilutoo.joyfulchatroom.R;
import com.ilutoo.joyfulchatroom.model.ChatModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatAdapter extends FirestoreRecyclerAdapter<ChatModel, ChatAdapter.ChatViewHolder> {

    static public final int VIEW_TYPE_TEXT = 0;
    static public final int VIEW_TYPE_IMAGE = 1;

    public ChatAdapter(@NonNull FirestoreRecyclerOptions<ChatModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i, @NonNull ChatModel chatModel) {
        chatViewHolder.message.setText(chatModel.getMessage());
        chatViewHolder.user_name.setText(chatModel.getUser_name());
        Glide.with(chatViewHolder.user_image.getContext().getApplicationContext())
                .load(chatModel.getUser_image_url())
                .into(chatViewHolder.user_image);

        if(!chatModel.getMessage().equals("")){
            chatViewHolder.message.setVisibility(View.VISIBLE);
        }else {
            chatViewHolder.message.setVisibility(View.GONE);
        }

        if(!chatModel.getChat_image().equals("")){
            Glide.with(chatViewHolder.chat_image.getContext().getApplicationContext())
                    .load(chatModel.getChat_image())
                    .into(chatViewHolder.chat_image);
            chatViewHolder.chat_image.setVisibility(View.VISIBLE);
        }else {
            chatViewHolder.chat_image.setVisibility(View.GONE);
        }

        String timestamp = "";

        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.KOREA);
            Date date = chatModel.getTimestamp();
            timestamp = format.format(date);
        }
        catch (Exception e){
            Log.e("Timestamp 에러", e.getMessage());
        }

        chatViewHolder.send_time.setText(timestamp);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_TEXT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_message, parent, false);
        }
        return new ChatViewHolder(view);
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView message;
        TextView user_name;
        TextView send_time;
        CircleImageView user_image;
        ImageView chat_image;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            message = itemView.findViewById(R.id.message);
            user_image = itemView.findViewById(R.id.user_image);
            chat_image = itemView.findViewById(R.id.chat_image);
            send_time = itemView.findViewById(R.id.timestamp);
        }
    }
}
