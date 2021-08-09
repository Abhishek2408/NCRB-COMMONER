package com.example.ncrbcommoner.ui;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotifyAdapter extends  RecyclerView.Adapter<NotifyAdapter.ViewHolder>{
    private List<User> myListData;
   Context context;

    public  NotifyAdapter(ViewGroup view){
        super();
        context = view.getContext();
    }

    public NotifyAdapter(List<User> myListData){
        this.myListData = myListData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final User user = myListData.get(position);
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = user.getUsername();
                Toast.makeText(view.getContext(),"Clicked on: "+username1,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,ShowActivity.class);
                i.putExtra("result",username1);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return myListData.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView username, email;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.email = itemView.findViewById(R.id.email);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}

