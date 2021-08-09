package com.example.ncrbcommoner.ui.Notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ncrbcommoner.User;
import com.example.ncrbcommoner.databinding.FragmentNotificationsBinding;


import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.ui.NotifyAdapter;
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
    private List<User> myListData;
    private NotifyAdapter adapter;
    DatabaseReference mbase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(getLayoutInflater(), container, false);
        View root = binding.getRoot();



        mbase = FirebaseDatabase.getInstance().getReference("Users");

        RecyclerView recyclerView = root.findViewById(R.id.recycleview_notify);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        myListData = new ArrayList<>();

        mbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot npsnapshot: snapshot.getChildren()){
                        User user = npsnapshot.getValue(User.class);
                        myListData.add(user);
                    }
                    adapter = new NotifyAdapter(myListData);
                    recyclerView.setAdapter(adapter);
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