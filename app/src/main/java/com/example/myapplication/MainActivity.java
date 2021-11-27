package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.etUsernameMain);
        etPassword = findViewById(R.id.etPasswordMain);
        auth = FirebaseAuth.getInstance();
    }


    public void login(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(this, "Username or password is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        } else {
                            Toast.makeText(MainActivity.this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }
                });
    }


    public boolean varifyEmail(String email) {
        String[] splitString = email.split("@");
        if (splitString.length != 2) {
            Toast.makeText(MainActivity.this, "incorrect username or password", Toast.LENGTH_SHORT).show();
            return false;


        }
        String username = splitString[0];
        String domain = splitString[1];
        String[] spiltusername = username.split("");
        if (spiltusername.length != 1) {
            Toast.makeText(this, "username or password are incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }
        char first = username.charAt(0);
        if (!(first >= 'a' & first <= 'z' || first == '_')) {
            Toast.makeText(this, "username or password are incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (username.length() > 70) {
            Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (username.length() < 3) {
            Toast.makeText(this, "username or password are incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            char p = username.charAt(i);
            if (!(p >= 'a' & p <= 'z' || p <= 'A' & p <= 'z' || p == '_' || p >= '0' & p <= '9')) {
                Toast.makeText(this, "username or password are incorrect", Toast.LENGTH_SHORT).show();
                return false;
            }


        }
        if (!(domain.split(".").length>=2 && domain.split(".").length<=5))
            return false;
        char firstd = domain.charAt(0);
        if (!(firstd>='a' & firstd<='z' || firstd=='_' || firstd>= 'A' & firstd<'Z')){
            Toast.makeText(this,"Username or email is false check again",Toast.LENGTH_SHORT).show();
            return false;
        }
        String[] dot = domain.split(".");
        String laststring = dot[dot.length-1];
        for (int i = 0 ; i < laststring.length() ; i ++){
            char p = laststring.charAt(i);
            if (!(p>='a' & p<='z' || p>='A' & p<='Z')){
               Toast.makeText(this,"username or email is false check again", Toast.LENGTH_SHORT).show();
               return false;
            }
        }
        return true;
    }

    public boolean validatePassword(String password) {
        int countsmall = 0, countcapital = 0, countwildcard = 0, countnumber = 0;
        if (password.length() > 30) {
            Toast.makeText(this, "username or password are incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(this, "username or password are incorrect ", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < password.length(); i++) {

            char p = password.charAt(i);
            if (p >= 'a' & p <= 'z') countsmall++;
            if (p <= 'A' & p <= 'Z') countcapital++;
            if (!(p >= 'a' & p <= 'z' || p <= 'A' & p <= 'Z' || p >= '0' & p <= '9'))
                countwildcard++;
            if (p >= '0' & p <= '9') countnumber++;

        }
        if (countsmall == 0 || countcapital == 0 || countwildcard == 0 || countnumber == 0) {
            Toast.makeText(this, "username or password are incorrect", Toast.LENGTH_SHORT).show();

            return false;

        }
        return true;
    }
}




