package com.example.latihansqlite;

import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;

    List<PersonBean> listPersonInfo;
    public RecyclerViewAdapter(Context context, List<PersonBean> listPersonInfo, OnUserClickListener listener){
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final PersonBean currentPerson = listPersonInfo.get(position);
        holder.tvName.setText(currentPerson.getName());
        holder.tvAge.setText(currentPerson.getAge() + "");

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(currentPerson,"Edit");
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(currentPerson, "Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public interface OnUserClickListener{
        void onUserClick(PersonBean currentPerson, String action);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvAge;
        ImageView imgDelete, imgEdit;
        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.mtvName);
            tvAge = itemView.findViewById(R.id.mtvAge);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }
}
