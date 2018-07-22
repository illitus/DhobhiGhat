package com.example.sarve.dhobhighat;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    //TextView signin;
    Button signup;
    EditText name, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.signup_new);

        signup=findViewById(R.id.btn_signup);
        name=findViewById(R.id.input_name);
        email=findViewById(R.id.input_email);
        pass=findViewById(R.id.input_password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=email.getText().toString();
                String nm=name.getText().toString();
                String pw=pass.getText().toString();
                SQLiteDatabase db=openOrCreateDatabase("signup",MODE_PRIVATE,null);
                db.execSQL("create table if not exists details(email varchar primary key, name varchar, pass varchar)");
                db.execSQL("insert into details values('"+em+"','"+nm+"','"+pw+"')");
                db.close();

                Toast.makeText(getApplicationContext(), "Account Successfully Created!", Toast.LENGTH_SHORT).show();

                finish();


            }
        });


    }

}
