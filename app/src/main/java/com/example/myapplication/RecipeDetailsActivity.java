package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayLinkedVariables;

public class RecipeDetailsActivity extends AppCompatActivity
{

    private EditText etName, etDesc, etAddress, etPhone;
    private ImageView ivPhoto;
    private ArrayLinkedVariables Picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        connectComponents();
        Intent i = this.getIntent();
        Recipe recipe = (Recipe) i.getSerializableExtra("recipe");

        etName.setText(Recipe.getName());
        etDesc.setText(Recipe.getDesc());
        etAddress.setText(Recipe.getAdress());
        etPhone.setText(Recipe.getPhone());
        //Picasso.get().load(Recipe.getPhoto()).into(ivPhoto);

    }
    private void connectComponents() {
        etName = findViewById(R.id.etNameRecipeDetails);
        etDesc = findViewById(R.id.etDescRecipeDetails);
        etAddress = findViewById(R.id.etAddressRecipeDetails);
        etPhone = findViewById(R.id.etPhoneRecipeDetails);
        ivPhoto = findViewById(R.id.ivPhotoRecipeDetails);
    }
}

