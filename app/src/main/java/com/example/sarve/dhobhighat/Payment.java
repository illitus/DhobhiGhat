package com.example.sarve.dhobhighat;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity{
    TextView subscription;
    TextView total;
    RadioButton r1,r2,rb;
    RadioGroup rg;
    TextView payment;
    ImageView wallet;
    ImageView cards;
    EditText number;
    EditText name;
    EditText mm;
    EditText cvv;
    Button b1;
    Button b2;
    TextView t1;

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_payment);
        subscription = (TextView)findViewById(R.id.textView);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        r1= findViewById(R.id.rb1);
        r2=findViewById(R.id.rb2);
        total=(TextView)findViewById(R.id.textView6);
        payment = (TextView) findViewById(R.id.textView2);
        wallet = (ImageView) findViewById(R.id.imageView);
        cards = (ImageView)findViewById(R.id.imageView2);
        number = (EditText)findViewById(R.id.edFirstName);
        name = (EditText)findViewById(R.id.edEmailAdress);
        mm = (EditText)findViewById(R.id.edLastName);
        cvv = (EditText)findViewById(R.id.edCVV);
        b2 = (Button)findViewById(R.id.button2);
        b1 = (Button)findViewById(R.id.button3);
        t1 = (TextView)findViewById(R.id.textView5);

        SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
        String emailid = sharedPreferences.getString("mail","temp");

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("Total:");
                total.setText("$9.99");
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("Total:");
                total.setText("$39.99");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please Select a Subscription Plan", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    SharedPreferences sharedPreferences = getSharedPreferences("your_preferences", Activity.MODE_PRIVATE);
                    String emailid = sharedPreferences.getString("mail","temp");

                    SQLiteDatabase db = openOrCreateDatabase("signup", MODE_PRIVATE, null);
                    db.execSQL("insert into subs values('"+emailid+"')");
                    db.close();
                    Intent intent = new Intent(context, thankyou.class);
                    startActivity(intent);

                    //int selected= rg.getCheckedRadioButtonId();
                    //rb= (RadioButton)findViewById(selected);
                    //total.setText(rb.getText().toString());

                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
