package com.example.sarve.dhobhighat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Address extends AppCompatActivity {

    Button upd,bck;
    EditText ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        upd=findViewById(R.id.button6);
        bck=findViewById(R.id.back);
        ad=findViewById(R.id.address);

        SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
        final String emailid = sharedPreferences.getString("mail","temp");

        SQLiteDatabase db=openOrCreateDatabase("signup",MODE_PRIVATE,null);
        db.execSQL("create table if not exists address(email varchar primary key, addr varchar)");
        //db.execSQL("insert into address values('"+emailid+"','empty')");

        try {
            db = openOrCreateDatabase("signup", MODE_PRIVATE, null);
            Cursor c = db.rawQuery("select * from address where email='" + emailid + "'", null);
            if (c.moveToFirst()) {
                do {
                    break;
                } while (c.moveToNext());
            } else {
                db.execSQL("insert into address values('"+emailid+"',null)");
            }
        }
        catch (Exception e){
            Toast.makeText(Address.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Cursor c = db.rawQuery("select addr from address where email='" + emailid + "'", null);
        String p = null;
        if (c.moveToFirst()){

            do {
                 p =c.getString(0);
            } while (c.moveToNext());
        }

        ad.setText(p);



        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String d = ad.getText().toString();
                SQLiteDatabase db=openOrCreateDatabase("signup",MODE_PRIVATE,null);
                db = openOrCreateDatabase("signup", MODE_PRIVATE, null);
                db.execSQL("update address set addr='"+d+"' where email='"+emailid+"'  ");
                Toast.makeText(getApplicationContext(),"Address successfully updated.",Toast.LENGTH_SHORT).show();

            }
        });

        db.close();


        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
