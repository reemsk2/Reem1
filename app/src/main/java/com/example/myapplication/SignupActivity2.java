package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity2 extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private String etConfirmPassword;
    private FirebaseAuth auth;

    public SignupActivity2(EditText etUsername, EditText etPassword, String etConfirmPassword, FirebaseAuth auth) {
        this.etUsername = etUsername;
        this.etPassword = etPassword;
        this.etConfirmPassword = etConfirmPassword;
        this.auth = auth;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
    }


public void signup (View view){
        //todo 1- get data from screen
    String username = etUsername.getText().toString();
    String password = etPassword.getText().toString();
    String confirmPassword = etConfirmPassword;
    if (username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty()){
        Toast.makeText(this, "Username or password is missing!", Toast.LENGTH_SHORT).show();
        return;
    }
    if (!password.equals(confirmPassword)){
        Toast.makeText(this, "Password do not match!!", Toast.LENGTH_SHORT).show();
        return;
    }
    // TODO: 3- Check username and password with Firebase Authentication
    auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // TODO: commands if successful
                    } else {


                        Toast.makeText(SignupActivity2.this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });

    }

}
