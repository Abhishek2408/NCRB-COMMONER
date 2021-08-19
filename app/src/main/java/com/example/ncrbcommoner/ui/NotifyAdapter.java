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
    private List<Fir_getNotify> myListData;
   Context context;


    public NotifyAdapter(List<Fir_getNotify> myListData){
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
        final Fir_getNotify fir_getNotify = myListData.get(position);
        holder.status.setText(fir_getNotify.getStatus());
        holder.statement.setText(fir_getNotify.getStatement());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = fir_getNotify.getName();
                Intent i = new Intent(context,ShowFirActivity.class);
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

        public TextView status, statement;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.status = itemView.findViewById(R.id.status);
            this.statement = itemView.findViewById(R.id.statement_txt);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}

