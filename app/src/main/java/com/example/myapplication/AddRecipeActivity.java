package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddRecipeActivity extends AppCompatActivity {
    private static final String TAG = "AddFoodActivity";
    private EditText etName, etDesc, etAddress, etPhone;
    private Spinner spFoods;
    private ImageView ivPhoto;
    private FirebaseServices fbs;
    StorageReference storageReference;
    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        getSupportActionBar().hide();
        connectComponents();
    }

    private void connectComponents() {
        etName = findViewById(R.id.etNameAddRest);
        etDesc = findViewById(R.id.etDescriptionAddRest);
        etAddress = findViewById(R.id.etAddressAddRest);
        etPhone = findViewById(R.id.etPhoneAddRest);
        spFoods = findViewById(R.id.spFoodsAddRest);
        ivPhoto = findViewById(R.id.ivPhotoAddRest);
        fbs = FirebaseServices.getInstance();
        spFoods.setAdapter(new ArrayAdapter<RestFoods>(this, android.R.layout.simple_list_item_1, RestFoods.values()));
        storageReference = fbs.getStorage().getReference();

    }

    public void add(View view) {
           // CHECK IF ANY FIELD IS EMPTY:
        String address , photo , phone , description , name , category;
        name = etName.getText().toString();
        description = etDesc.getText().toString();
        address = etAddress.getText().toString();
        phone = etPhone.getText().toString();
        category = spFoods.getSelectedItem().toString();
        if (ivPhoto.getDrawableState() == null)
            photo = "no_image";
        else photo = storageReference.getDownloadUrl().toString();

        if (name.trim().isEmpty() || description.trim().isEmpty() || address.trim().isEmpty() ||
                phone.trim().isEmpty() || category.trim().isEmpty() || photo.trim().isEmpty()) {
            Toast.makeText(this, R.string.err_fields_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        Recipe rest = new Recipe(address, photo , phone , description , name , RestFoods.valueOf(category));
        fbs.getFirestore().collection("Recipe")
                .add(rest)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 40);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        filePath = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        ivPhoto.setBackground(null);
                        ivPhoto.setImageBitmap(bitmap);
                        uploadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }


        private void uploadImage(){
    if (filePath != null) {

        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference ref
                = storageReference
                .child(
                        "images/"
                                + UUID.randomUUID().toString());

                ref.putFile(filePath).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(
                                UploadTask.TaskSnapshot taskSnapshot)
                        {


                            progressDialog.dismiss();
                            Toast
                                    .makeText(AddRecipeActivity.this,
                                            "Image Uploaded!!",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e)
                    {

                        // Error, Image not uploaded
                        progressDialog.dismiss();
                        Toast
                                .makeText(AddRecipeActivity.this,
                                        "Failed " + e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnProgressListener(
                        new OnProgressListener<UploadTask.TaskSnapshot>() {

                            // Progress Listener for loading
                            // percentage on the dialog box

                        @Override
                        public void onProgress(
                                UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress
                                    = (100.0
                                    * taskSnapshot.getBytesTransferred()
                                    / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage(
                                    "Uploaded "
                                            + (int) progress + "%");
                        }

                    });

            }


    }

}