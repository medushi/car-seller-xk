package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.carsellerxk.Models.NewPostUploadModel;
import com.example.carsellerxk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

public class NewPostActivity extends AppCompatActivity {

    DatabaseReference _dbReference;
    StorageReference _storageReference;
    private static final int GALLERY_REQ_CODE = 100;
    private static final int CAMERA_REQ_CODE = 101;
    EditText etTitle, etPrice;
    Spinner spinnerCity, spinnerTypeOfAcceleration, spinnerManufacturer, spinnerYearOfProduction;
    ImageView img1, img2, img3;
    Button btnLoadImages, btnCaptureImages, btnSubmitPost;
    LinearLayout postImagesHolder;

    private static List<Uri> _localImagesUri = new ArrayList<>();
    private List<String> _remoteImagesUrl = new ArrayList<>();
    private String _nextPostid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        etTitle = findViewById(R.id.etNewPostTitle);
        etPrice = findViewById(R.id.etPrice);
        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerTypeOfAcceleration = findViewById(R.id.spinnerAccelerateType);
        spinnerManufacturer = findViewById(R.id.spinnerManufacturer);
        spinnerYearOfProduction = findViewById(R.id.spinnerYearOfProduction);
        btnLoadImages = findViewById(R.id.btnNgarkoFoto);
        btnCaptureImages = findViewById(R.id.btnShkrepFoto);
        btnSubmitPost = findViewById(R.id.btnPostoShpalljen);
        postImagesHolder = findViewById(R.id.containerNewPostImages);
        img1 = findViewById(R.id.ivimg1);
        img2 = findViewById(R.id.ivimg2);
        img3 = findViewById(R.id.ivimg3);
        _storageReference = FirebaseStorage.getInstance().getReference();
        _dbReference = FirebaseDatabase.getInstance().getReference().child("posts");

        _dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                _nextPostid = "pid" + (int) (dataSnapshot.getChildrenCount() + 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnLoadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(NewPostActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(NewPostActivity.this,"Veqse ke leje per casje ne fajlla",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(Intent.createChooser(intent, "Zgjidh foto"), GALLERY_REQ_CODE);
                }
                else{
                    if(ActivityCompat.shouldShowRequestPermissionRationale(NewPostActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        new AlertDialog.Builder(NewPostActivity.this).setTitle("kerkohet cajsa").setMessage("Duhet casja per me vendos foto")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(NewPostActivity.this,new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE},GALLERY_REQ_CODE);
                                    }
                                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                    else{
                        ActivityCompat.requestPermissions(NewPostActivity.this,new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE},GALLERY_REQ_CODE);
                    }
                }

            }
        });

        btnCaptureImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(NewPostActivity.this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(NewPostActivity.this,"Veqse ke leje per casje ne kamere",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQ_CODE);
                }
                else{
                    if(ActivityCompat.shouldShowRequestPermissionRationale(NewPostActivity.this,Manifest.permission.CAMERA)){
                        new AlertDialog.Builder(NewPostActivity.this).setTitle("kerkohet cajsa").setMessage("Duhet casja per me vendos foto")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(NewPostActivity.this,new String [] {Manifest.permission.CAMERA},CAMERA_REQ_CODE);
                                    }
                                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                    else{
                        ActivityCompat.requestPermissions(NewPostActivity.this,new String [] {Manifest.permission.CAMERA},CAMERA_REQ_CODE);
                    }
                }
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, CAMERA_REQ_CODE);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.newpost);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.newpost:
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext()
                                , SavedActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                , ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext()
                                , MoreActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        btnSubmitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_localImagesUri.isEmpty()) {
                    for (Uri imageUri : _localImagesUri) {
                        uploadPostImagesToStorage(imageUri);
                    }

                    _dbReference.child(_nextPostid).setValue(new NewPostUploadModel(
                            _nextPostid, etTitle.getText().toString(), spinnerCity.getSelectedItem().toString(),
                            spinnerYearOfProduction.getSelectedItem().toString(), spinnerTypeOfAcceleration.getSelectedItem().toString(),
                            spinnerManufacturer.getSelectedItem().toString(), parseDouble(etPrice.getText().toString()), _remoteImagesUrl
                    ));
                    Toast.makeText(getApplicationContext(), "Tash po bohet upload modeli", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Deshtim gjate procesimit te fotografive", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQ_CODE && resultCode == RESULT_OK && data != null) {
            //Uri imageData = data.getData();
            int numberOfImagesUploaded = data.getClipData().getItemCount();
            for (int start = 0; start < numberOfImagesUploaded; start++) {
                Uri imageUri = data.getClipData().getItemAt(start).getUri();
                if (start == 0) {
                    img1.setImageURI(imageUri);
                    _localImagesUri.add(imageUri);
                }
                if (start == 1) {
                    img2.setImageURI(imageUri);
                    _localImagesUri.add(imageUri);
                } else if (start == 2)
                    img3.setImageURI(imageUri);
                _localImagesUri.add(imageUri);
            }
        }
        if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_OK && data != null) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            boolean imageHasbeenPlaced = false;
            if (img1.getDrawable() == null) {
                img1.setImageBitmap(imageBitmap);
                imageHasbeenPlaced = true;
                _localImagesUri.add(getImageUri(this, imageBitmap));
                //uploadToStorage(getImageUri());
            }
            if (!imageHasbeenPlaced) {
                if (img2.getDrawable() == null) {
                    img2.setImageBitmap(imageBitmap);
                    imageHasbeenPlaced = true;
                    _localImagesUri.add(getImageUri(this, imageBitmap));
                }
            }
            if (!imageHasbeenPlaced) {
                if (img3.getDrawable() == null) {
                    img3.setImageBitmap(imageBitmap);
                    imageHasbeenPlaced = true;
                    _localImagesUri.add(getImageUri(this, imageBitmap));
                }
            }
            if (img1.getDrawable() != null && img2.getDrawable() != null && img3.getDrawable() != null)
                Toast.makeText(this, "Vetem se keni 3 foto te perzgjedhura", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== GALLERY_REQ_CODE || requestCode==CAMERA_REQ_CODE) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(NewPostActivity.this,"u lejua casja",Toast.LENGTH_LONG).show();

            }
        }
    }

    private void uploadPostImagesToStorage(Uri imageUriToUpload) {
        final long currentDateMillis = new Date().getTime();
        _storageReference.child("postImage_" + currentDateMillis).putFile(imageUriToUpload)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        _storageReference.child("postImage_" + currentDateMillis).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String profileImageUrl = task.getResult().toString();
                                Log.i("URL", profileImageUrl);
                                Toast.makeText(NewPostActivity.this, profileImageUrl, Toast.LENGTH_LONG).show();
                                _remoteImagesUrl.add(profileImageUrl);

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewPostActivity.this, "aaa " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //checkperimision();
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title"+ Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }
}
