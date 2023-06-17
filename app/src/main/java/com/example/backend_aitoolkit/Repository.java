package com.example.backend_aitoolkit;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Repository {

    private FirebaseStorage firebaseStorage;

    private AI_model sound=new AI_model();
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String image_firebase_url=null;

    public static  int MUSINO=0;
    private Context context;


    public Repository(Context context) {
        this.context=context;
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
    }

    public void uploadindatabse(AI_model sound){
        databaseReference=firebaseDatabase.getReference().child("aidetails").child(sound.getAI_Name()+MUSINO);

        databaseReference.setValue(sound).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.v("TAG","uploaded sound");
                    MainActivity.progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
                }else{
                    Log.v("TAG","failed to upload sound");
                    MainActivity.progressBar.setVisibility(View.GONE);
                }

            }
        });


    }
    public void upload_image(Uri image_uri,AI_model ai_model){
        MUSINO++;
        storageReference=firebaseStorage.getReference().child(ai_model.getAI_Name()+MUSINO);

        storageReference.putFile(image_uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        image_firebase_url=uri.toString();
                        ai_model.setProfile_uri(image_firebase_url);
                        uploadindatabse(ai_model);
                    }
                });
            }
        });

    }
}
