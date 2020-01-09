package com.example.insertfirebase;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button insert;
    Button logout;
    EditText t;
    GetUserInfo userInfo;
    Double lat;
    Double lon;
    TextView aaa;
    Button rrr;


    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("message1");
    FirebaseAuth mauth;
    FirebaseUser user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfo = new GetUserInfo();
        t = findViewById(R.id.editText);
        rrr = findViewById(R.id.RRRR);
        aaa = findViewById(R.id.Veify);

        rrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Retreive.class);
                //intent.putExtra("name",t.getText().toString());
                startActivity(intent);
            }
        });


        //fetching data
        String name1 = t.getText().toString().trim();

        ref = FirebaseDatabase.getInstance().getReference("Current location").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(name1);


        logout = findViewById(R.id.btn_logout);
        insert = findViewById(R.id.button);
        mauth = FirebaseAuth.getInstance();
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final gpstracker g = new gpstracker(getApplication());
                final Location l = g.getlocation();
                if (l != null) {
                    lat = l.getLatitude();
                    lon = l.getLongitude();
                    String name1 = t.getText().toString().trim();
                    CurrentLocation c = new CurrentLocation(lat, lon, name1);
                    Toast.makeText(getApplicationContext(), "Lat" + lat + "\n Lon :" + lon, Toast.LENGTH_LONG).show();

                    FirebaseDatabase.getInstance().getReference("Current location")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(name1)
                            .setValue(c);


                }

//                FirebaseDatabase.getInstance().getReference("users")
//                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .setValue("Topics in computer science");
                final Location[] locations = new Location[100];


                //myRef.setValue("android project");
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    userInfo = ds.getValue(GetUserInfo.class);
//                    Double lalal = ds.child("laltitude").getValue(Double.class);
//                    Double lonlon = ds.child("longitude").getValue(Double.class);
                    Double lallal = userInfo.getLaltitude();
                    Double lonlon = userInfo.getLongitude();

                    aaa.setText(String.valueOf(lallal));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = mauth.getCurrentUser();
    }
}
