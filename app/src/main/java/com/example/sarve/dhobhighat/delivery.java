package com.example.sarve.dhobhighat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class delivery extends AppCompatActivity {

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        confirm=findViewById(R.id.button9);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
        datePicker.updateDate(2016, 5, 22);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(delivery.this, "Order date successfully selected.", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(delivery.this, Home.class);
                startActivity(intent);
            }
        });


    }
}
