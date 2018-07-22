package com.example.sarve.dhobhighat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView address, order, subscription, past, supp, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_new);

        subscription=findViewById(R.id.textView8);
        profile=findViewById(R.id.textView10);
        address=findViewById(R.id.textView13);
        order=findViewById(R.id.textView9);
        supp=findViewById(R.id.textView14);
        past=findViewById(R.id.textView12);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Menu.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
        final String emailid = sharedPreferences.getString("mail","temp");

        SQLiteDatabase db=openOrCreateDatabase("signup",MODE_PRIVATE,null);
        db.execSQL("create table if not exists subs(email varchar primary key)");
        //db.execSQL("insert into details values('"+emailid+"')");
        db.close();

        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    SQLiteDatabase db = openOrCreateDatabase("signup", MODE_PRIVATE, null);
                    Cursor c = db.rawQuery("select * from subs where email='" + emailid + "'", null);
                    if (c.moveToFirst()) {
                        do {
                            Toast.makeText(Home.this, "Subscription up to date", Toast.LENGTH_SHORT).show();

                        } while (c.moveToNext());
                    } else {
                        Intent intent=new Intent(Home.this, Payment.class);
                        startActivity(intent);
                    }
                }
                catch (Exception e){
                    Toast.makeText(Home.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, order.class);
                startActivity(intent);
            }
        });

        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Support.class);
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Address.class);
                startActivity(intent);
            }
        });

        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Past.class);
                startActivity(intent);
            }
        });


    }
}
