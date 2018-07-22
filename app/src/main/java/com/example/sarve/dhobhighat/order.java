package com.example.sarve.dhobhighat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

public class order extends AppCompatActivity {


    RadioGroup rg;

    Button bck;
    Button proc;
    EditText clothes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        rg=findViewById(R.id.radioGroup);



        clothes=findViewById(R.id.editText11);
        bck=findViewById(R.id.calculate);
        proc=findViewById(R.id.button8);


        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fin;
                fin=clothes.getText().toString();
                if(rg.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please Select a Subscription Plan", Toast.LENGTH_SHORT).show();
                }
                else if (Objects.equals(fin, "0")){
                    Toast.makeText(order.this, "Enter the amount of clothes", Toast.LENGTH_SHORT).show();
                }

                else{
                    SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
                    String emailid = sharedPreferences.getString("mail","temp");

                    SQLiteDatabase db=openOrCreateDatabase("signup",MODE_PRIVATE,null);
                    db.execSQL("create table if not exists ord(email varchar , pass varchar)");
                    db.execSQL("insert into ord values('"+emailid+"','"+fin+"')");
                    db.close();

                    Intent intent=new Intent(order.this, date.class);
                    startActivity(intent);
                }
            }
        });






    }
}
