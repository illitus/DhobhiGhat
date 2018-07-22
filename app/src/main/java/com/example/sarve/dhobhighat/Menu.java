package com.example.sarve.dhobhighat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class Menu extends AppCompatActivity {
    TextView nm,em;
    ImageView im;
    Button out;
    Button hom;
    GoogleApiClient mGoogleApiClient;

    @Override

    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        nm= findViewById(R.id.gname);

        em= findViewById(R.id.gmail);
        im= findViewById(R.id.img1);
        hom= findViewById(R.id.home);
        out= findViewById(R.id.signout);


        final Intent in=getIntent();
        String name=in.getStringExtra("name");
        String named = name;
        String email=in.getStringExtra("email");
        String emaild=email;
        final String img_url=in.getStringExtra("img_url");
        String imgd=img_url;

        final SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
        String emailid = sharedPreferences.getString("mail","temp");

        SharedPreferences sharedPreferences1 = getSharedPreferences("img", Activity.MODE_PRIVATE);
        String img1 = sharedPreferences.getString("url","temp");



        nm.setText(name);
        em.setText(emailid);
        Glide.with(this).load(img1).into(im);



        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Home.class);
                startActivity(intent);
            }
        });





        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                                Toast.makeText(getApplicationContext(),"Log Out Successful",Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences1 = getSharedPreferences("img", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("url", null);
                                editor.apply();
                                Intent i=new Intent(getApplicationContext(),Login.class);
                                startActivity(i);
                            }
                        });
            }
        });







    }




}
