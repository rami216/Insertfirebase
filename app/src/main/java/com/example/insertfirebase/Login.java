package com.example.insertfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    FirebaseAuth mauth ;
    EditText userlogin;
    EditText userloginpass;
    Button loginbtn;
    Button already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userlogin = (EditText)findViewById(R.id.text_username);
        userloginpass= (EditText)findViewById(R.id.text_password);

        loginbtn = (Button)findViewById(R.id.btn_login);
        already = (Button)findViewById(R.id.btn_have);
        mauth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginemail = userlogin.getText().toString();
                String loginpass = userloginpass.getText().toString();
                if(!TextUtils.isEmpty(loginemail) && !TextUtils.isEmpty(loginpass)){

                    mauth.signInWithEmailAndPassword(loginemail,loginpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(Login.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                String error = task.getException().getMessage();
                                Toast.makeText(Login.this,"Error"+error,Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                }else {
                    Toast.makeText(getApplicationContext(),"please please fill all required fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mauth.getCurrentUser();

        if(currentuser != null)
        {
            Intent mainintent = new Intent(Login.this,MainActivity.class);
            startActivity(mainintent);
            finish();
        }
    }
}
