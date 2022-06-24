package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private FirebaseAuth auth;
    private Utilities utils;
    private FirebaseServices fbs;
    Button openDialog;
    TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        connectComponent();
        getSupportActionBar().hide();


    }

    private void connectComponent() {
        etUsername = findViewById(R.id.etUsernameMain);
        etPassword = findViewById(R.id.etPasswordMain);
        utils = Utilities.getInstance();
        fbs = FirebaseServices.getInstance();
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
                            Intent i = new Intent(MainActivity.this, AllRecipesActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }


    public void gotoAllRecipes(View view) {
        Intent i = new Intent(MainActivity.this, AllRecipesActivity.class);
        startActivity(i);
    }
}





