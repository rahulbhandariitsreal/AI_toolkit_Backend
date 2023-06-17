package com.example.backend_aitoolkit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;

    public static ProgressBar progressBar;

    private ImageView imageView;
    private EditText editTextName, editTextRelated, editTextDescription, editTextStars, editTextOrigin, editTextWebsite;
    Button button;

    private AI_model ai_model;

    private Repository repository;

    private Uri selectedimage;
    public static int uid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);


        repository=new Repository(this);
        imageView = findViewById(R.id.imageView);
        editTextName = findViewById(R.id.editTextName);
        editTextRelated = findViewById(R.id.editTextRelated);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextStars = findViewById(R.id.editTextStars);
        editTextOrigin = findViewById(R.id.editTextOrigin);
        editTextWebsite = findViewById(R.id.editTextWebsite);
        button = findViewById(R.id.uploadai);
        progressBar = findViewById(R.id.progressbar);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String related = editTextRelated.getText().toString();
                String description = editTextDescription.getText().toString();
                int stars = Integer.parseInt(editTextStars.getText().toString());
                String origin = editTextOrigin.getText().toString();
                String website = editTextWebsite.getText().toString();


                if (name.isEmpty() || related.isEmpty() || description.isEmpty() ||
                        origin.isEmpty() || website.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Do something with the non-empty fields
                    // For example, you can proceed with form submission or any other desired action
                    progressBar.setVisibility(View.VISIBLE);
                    uid++;
                    saveshared(uid);
                    ai_model = new AI_model(name, related, description, stars, origin, website);
                    ai_model.setU_id(uid);
                    repository.upload_image(selectedimage,ai_model);
                }

            }
        });


    }


    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            selectedimage = data.getData();
            // Do something with the selected image URI

        }
    }

    public void saveshared(int i) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("uid", i);
        editor.apply();
    }
}