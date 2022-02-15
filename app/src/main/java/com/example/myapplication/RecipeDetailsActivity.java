package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

public class RecipeDetailsActivity extends AppCompatActivity
{
    private EditText etName, etDesc, etAddress, etPhone;
    private Spinner spFoods;
    private ImageView ivPhoto;
    private FirebaseServices fbs;
    StorageReference storageReference;
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe);

        connectComponents();
        Intent i = this.getIntent();
        Recipe recipe = (Recipe) i.getSerializableExtra("recipe");

        etName.setText(Recipe.getName());
        etDesc.setText(Recipe.getName());
        etAddress.setText(Recipe.getName());
        etPhone.setText(Recipe.getName());
        Picasso.get().load(Recipe.getPhoto()).into(ivPhoto);
    }
    private void connectComponents() {
        etName = findViewById(R.id.tvNameRecipeDetails);
        etDesc = findViewById(R.id.etDescRecipeDetails);
        etAddress = findViewById(R.id.etAddressRecipeDetails);
        etPhone = findViewById(R.id.etPhoneRecipeDetails);
        ivPhoto = findViewById(R.id.etPhotoRecipeDetails);
}
}

