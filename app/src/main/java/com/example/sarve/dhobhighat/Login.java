package com.example.sarve.dhobhighat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    TextView register;
    Button glogin;
    EditText eml, password;
    GoogleApiClient googleApiClient;
    static final int REQ_CODE = 9001;
    String name,email,img_url;
    RelativeLayout login;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);


        setContentView(R.layout.login_new);


        register= findViewById(R.id.textView2);
        glogin= findViewById(R.id.button5);
        login=findViewById(R.id.rl);

        eml= findViewById(R.id.editText);
        password=findViewById(R.id.editText2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = openOrCreateDatabase("signup", MODE_PRIVATE, null);
                    Cursor c = db.rawQuery("select pass from details where email='" + eml.getText().toString() + "'", null);
                    if (c.moveToFirst()) {
                        do {
                            if (c.getString(0).equals(password.getText().toString())) {
                                Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();




                                SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("mail", eml.getText().toString());
                                editor.apply();

                                Cursor d = db.rawQuery("select name from details where email='" + eml.getText().toString() + "'", null);
                                String nm = "temp";
                                if(d.moveToNext()) {
                                    nm =d.getString(0);
                                }
                                Intent intent = new Intent(Login.this, Menu.class);
                                intent.putExtra("name",nm);
                                intent.putExtra("email",eml.getText().toString());
                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, "Please check your password", Toast.LENGTH_SHORT).show();
                            }

                        } while (c.moveToNext());
                    } else {
                        Toast.makeText(Login.this, "Please check your email", Toast.LENGTH_SHORT).show();
                    }

                    db.close();
                }
                catch (Exception e){
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }


        });



        glogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient= new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();



    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void handleResult(GoogleSignInResult result){

            GoogleSignInAccount account = result.getSignInAccount();
            name = account.getDisplayName();
            email = account.getEmail();
            img_url = account.getPhotoUrl().toString();



            updateUI();

    }


    private void updateUI()
    {

            Intent in=new Intent(Login.this,Menu.class);

            in.putExtra("name",name);
            in.putExtra("email",email);
            in.putExtra("img_url",img_url);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mail", email);
        editor.apply();

        SharedPreferences sharedPreferences1 = getSharedPreferences("img", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor.putString("url", img_url);
        editor.apply();

        SharedPreferences sharedPreferences2 = getSharedPreferences("name", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();


        startActivity(in);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==REQ_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }



}

