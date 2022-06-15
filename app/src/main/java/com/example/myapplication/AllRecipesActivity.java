package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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
    }
0

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
                                    Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                                }
                            }
                        });
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
}

