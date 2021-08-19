package com.example.ncrbcommoner.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncrbcommoner.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AppointAdapter extends RecyclerView.Adapter<AppointAdapter.viewHolder>{

    private List<Notify_appointdata> apptlistdata;
    Context context;

    public  AppointAdapter(List<Notify_appointdata> apptlistdata){
        this.apptlistdata = apptlistdata;
    }

    @NotNull
    @Override
    public AppointAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_appoint_items,parent,false);
        AppointAdapter.viewHolder vholder = new AppointAdapter.viewHolder(listItem);
        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointAdapter.viewHolder holder, int position) {
        final Notify_appointdata appointdata = apptlistdata.get(position);
        holder.status.setText(appointdata.getStatus());
        holder.user.setText(appointdata.getUser());
        holder.name.setText(appointdata.getCommoner_name());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = appointdata.getCommoner_name();
                Intent i = new Intent(context,ShowActivity.class);
                i.putExtra("result",username1);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return apptlistdata.size();
    }

    public class viewHolder extends  RecyclerView.ViewHolder{
        public RelativeLayout relativeLayout;
        public TextView user,status,name;

        public viewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.user = itemView.findViewById(R.id.user);
            this.status = itemView.findViewById(R.id.status);
            this.name = itemView.findViewById(R.id.namebox);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout2);
        }
    }

}
