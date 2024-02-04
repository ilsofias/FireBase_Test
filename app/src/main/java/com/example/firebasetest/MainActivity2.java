package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String choosenName = getIntent().getStringExtra("name");
        String choosenEmail = getIntent().getStringExtra("email");
        String choosenCountry = getIntent().getStringExtra("country");

        TextView getName = (TextView) findViewById(R.id.getName);
        TextView getEmail = (TextView) findViewById(R.id.getEmail);
        TextView getCountry = (TextView)findViewById(R.id.getCountry);

        getName.setText(choosenName);
        getEmail.setText(choosenEmail);
        getCountry.setText(choosenCountry);

    }
}