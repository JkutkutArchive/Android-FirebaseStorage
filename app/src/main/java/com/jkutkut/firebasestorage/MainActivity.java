package com.jkutkut.firebasestorage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jkutkut.firebasestorage.model.Profile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference dbRef;
    private StorageReference storageRef;

    private EditText etxtName, etxtEmail;
    private Button btnSubmit;
    private ImageButton ibtnImage;
    private ImageView ivPreview;

    private Uri selectedUri;

    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        selectedUri = result.getData().getData();
                        Glide.with(ivPreview.getContext())
                                .load(selectedUri)
                                .into(ivPreview);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference("Profiles");
        storageRef = FirebaseStorage.getInstance().getReference().child("Photos");

        etxtName = findViewById(R.id.etxtName);
        etxtEmail = findViewById(R.id.etxtEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        ibtnImage = findViewById(R.id.ibtnImage);
        ivPreview = findViewById(R.id.ivPreview);

        btnSubmit.setOnClickListener(this);
        ibtnImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {

        }
        else if (v.getId() == R.id.ibtnImage) {
            addPhoto();
        }
    }

    private void addPhoto() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult.launch(Intent.createChooser(i, "Choose an image"));
    }
}