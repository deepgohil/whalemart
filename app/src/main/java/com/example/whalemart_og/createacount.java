package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class createacount extends AppCompatActivity {
    Button createaccount;
    EditText name, email, phone, password;
    TextView erromsg, textforimage;
    ProgressBar progressBar;
    ImageView imageView;
    private Uri uri;
    int SELECT_IMAGE_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        createaccount = findViewById(R.id.createacc);
        erromsg = findViewById(R.id.errormsg);
        progressBar = findViewById(R.id.progressBar);
        textforimage = findViewById(R.id.textforimage);
        imageView = findViewById(R.id.adduserimage);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"titel"),SELECT_IMAGE_CODE);



            }
        });



        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(createacount.this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    Toast.makeText(createacount.this, "Please Enter valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(createacount.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().length()<6){
                    Toast.makeText(createacount.this, "Please Enter 6 or more than digit password", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(createacount.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(uri == null){
                    Toast.makeText(createacount.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else {
                    register();
                }
            }
        });

    }

    private void register() {
        progressBar.setVisibility(View.VISIBLE);


        FirebaseAuth.getInstance().
                createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        upload();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(createacount.this, "Failed. "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    void upload(){
        final StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("Temp/"+System.currentTimeMillis()+".png");
        riversRef.putFile(uri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressBar.setVisibility(View.GONE);
                Log.i("dscgjdshv", "onFailure: "+exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        insertUserInfo(uri);
                    }
                });

            }
        });
    }


    private void insertUserInfo(Uri uri) {



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        HashMap<String,Object> map = new HashMap<>();
                        map.put("email",email.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("password",password.getText().toString());
                        map.put("phone",phone.getText().toString());
                        map.put("image",uri.toString());
                        map.put("type","customer");
                        map.put("token",token);

                        FirebaseDatabase.getInstance().getReference()
                                .child("User")
                                .child(FirebaseAuth.getInstance().getUid())
                                .setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(createacount.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(createacount.this, MainActivity.class));
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressBar.setVisibility(View.GONE);
                                        Log.i("dscgjdshv", "onFailure: "+e.getMessage());
                                    }
                                });





                    }
                });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            uri=data.getData();
            imageView.setImageURI(uri);
        }
    }


}
