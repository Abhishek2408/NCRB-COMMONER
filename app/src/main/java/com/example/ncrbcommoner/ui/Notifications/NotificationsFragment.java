package com.example.ncrbcommoner.ui.Notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ncrbcommoner.databinding.FragmentNotificationsBinding;


import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.ui.AppointAdapter;
import com.example.ncrbcommoner.ui.Fir_getNotify;
import com.example.ncrbcommoner.ui.NotifyAdapter;
import com.example.ncrbcommoner.ui.Notify_appointdata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private List<Fir_getNotify> myListData;
    private List<Notify_appointdata> apptlistData;
    private NotifyAdapter adapter1;
    private AppointAdapter adapter2;
    DatabaseReference mbase_appfir, mbase_appapt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(getLayoutInflater(), container, false);
        View root = binding.getRoot();

//Notification for FIR

        mbase_appfir = FirebaseDatabase.getInstance().getReference("Approved Cases");

        RecyclerView recyclerView1 = root.findViewById(R.id.recycleview_notify);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(root.getContext()));
        myListData = new ArrayList<>();

        mbase_appfir.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot npsnapshot: snapshot.getChildren()){
                        Fir_getNotify fir_getNotify = npsnapshot.getValue(Fir_getNotify.class);
                        myListData.add(fir_getNotify);
                    }
                    adapter1 = new NotifyAdapter(myListData);
                    recyclerView1.setAdapter(adapter1);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        //Notification for Appointment

        mbase_appapt = FirebaseDatabase.getInstance().getReference("Approved Appointment");

        RecyclerView recyclerView2 = root.findViewById(R.id.recycleview_appoint);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(root.getContext()));
        apptlistData = new ArrayList<>();

        mbase_appapt.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot npsnapshot: snapshot.getChildren()){
                        Notify_appointdata notifyAppointdata = npsnapshot.getValue(Notify_appointdata.class);
                        apptlistData.add(notifyAppointdata);
                    }
                    adapter2 = new AppointAdapter(apptlistData);
                    recyclerView2.setAdapter(adapter2);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return  root;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}