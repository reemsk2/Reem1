package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class recipeDetailActivity extends AppCompatActivity {

    private String tvName, tvDescription, tvAddress, tvPhone, ivPhoto;

    private String address;
    private RestFoods category;
    private String phone;
    private String photo;
    private String name;
    private String description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        connectComponents();
        Intent i = this.getIntent();
        Recipe rest = (Recipe) i.getSerializableExtra("recipe");

        tvName.setText(rest.getName());
        tvDescription.setText(rest.getDescription());
        tvAddress.setText(rest.getAddress());
        tvCategory.setText(rest.getCategory().toString());
        tvPhone.setText(rest.getPhone());
        Picasso.get().load(rest.getPhoto()).into(ivPhoto);
    }
    private void connectComponents() {
        tvName = findViewById(R.id.tvNamerecipeDetail);
        tvDescription = findViewById(R.id.tvDescriptionrecipeDetail);
        tvAddress = findViewById(R.id.tvAddressrecipeDetail);
        tvPhone = findViewById(R.id.tvPhonerecipeDetail);
        ivPhoto = findViewById(R.id.ivPhotorecipeDetail);
    }
}