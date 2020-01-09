package com.example.insertfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Retreive extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    GetUserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive);
        Intent intent = getIntent();
        database = FirebaseDatabase.getInstance();
        userInfo = new GetUserInfo();
        listView = findViewById(R.id.listview1);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.user_info, R.id.userinfo, list);
        ref = database.getReference("Current location").child(FirebaseAuth.getInstance().getUid());


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    userInfo = ds.getValue(GetUserInfo.class);
                    list.add(userInfo.getName());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
