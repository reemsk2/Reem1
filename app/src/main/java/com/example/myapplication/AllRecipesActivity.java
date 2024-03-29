package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AllRecipesActivity extends AppCompatActivity {
    private RecyclerView rvAllRecipe;
    AdapterRecipe adapter;
    FirebaseServices fbs;
    ArrayList<Recipe> recipes;
    MyCallback myCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe);
        fbs = FirebaseServices.getInstance();
        recipes = new ArrayList<Recipe>();
        readData();
        myCallback = new MyCallback() {
            @Override
            public void onCallback(List<Recipe> recipesList) {
                RecyclerView recyclerView = findViewById(R.id.rvAllRecipe);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterRecipe(getApplicationContext(), recipes);
                recyclerView.setAdapter(adapter);
            }
        };

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("RecipeApp");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miProfile:
                gotoAddRecipe();
                return true;

            case R.id.miSettings:
                showDialog1();
                fbs.getAuth().signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog1() {
        new AlertDialog.Builder(this)
                .setTitle("Alert!")
                .setMessage("Are you sure you want to logout!?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void gotoAddRecipe() {
    }


    private void readData() {
            try {

                fbs.getFirestore().collection("recipes")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        recipes.add(document.toObject(Recipe.class));
                                    }
                                    myCallback.onCallback(recipes);
                                } else {
                                    Log.e("AllRecipesActivity: readData()", "Error getting documents.", task.getException());
                                }
                            }
                        });

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    public void gotoAddRecipe(View view) {
        Intent i = new Intent(this, AddRecipeActivity.class);
        startActivity(i);
    }

}

