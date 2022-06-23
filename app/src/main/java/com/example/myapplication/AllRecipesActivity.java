package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
            public void onCallback(List<Recipe> restsList) {
                RecyclerView recyclerView = findViewById(R.id.rvRecipesAllRecipe);
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
            //case R.id.miSearch:
            // User chose the "Settings" item, show the app settings UI...
            //return true;

            case R.id.miProfile:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.miSettings:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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
}

