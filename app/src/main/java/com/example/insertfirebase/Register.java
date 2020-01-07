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

public class Register extends AppCompatActivity {
    Button register_btn;
    Button haveanaccount;
    EditText name;
    EditText email;
    EditText password;
    FirebaseAuth matuh = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_btn = (Button)findViewById(R.id.btn_login);
        haveanaccount = (Button)findViewById(R.id.btn_have);
        name = (EditText)findViewById(R.id.text_username);
        email = (EditText)findViewById(R.id.text_email);
        password = (EditText)findViewById(R.id.text_password);


        //registration button
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();

                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(useremail)&& !TextUtils.isEmpty(userpassword))
                {
                    matuh.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(Register.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                String error = task.getException().getMessage();
                                Toast.makeText(Register.this,"Error"+error,Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }else {
                    Toast.makeText(Register.this,"Please fill all missed texts!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        haveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = matuh.getCurrentUser();

        if(currentuser != null)
        {
            Intent mainintent = new Intent(Register.this,MainActivity.class);
            startActivity(mainintent);
            finish();
        }
    }
}
