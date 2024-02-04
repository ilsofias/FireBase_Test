package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {
    public static AutoCompleteTextView userName, emailAdress, countryCode;
    public static String selectedName, selectedEmail, selectedCountry,  nameCheck, emailCheck,countryCheck;
    private ArrayList<String> val=new ArrayList<>();
    private Button logIn;
    private TextView clear;
    //FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        initCompponents();

        String[] nameSuggestions = {"Sofia", "Dwight", "Berlene", "Matthew", "Adrian", "Mica", "Curtis", "Dan", "Ronn", "Clark"};
        String[] emailSuggestions = {"Sofia@gmail.com", "Dwight@gmail.com", "Berlene@gmail.com", "Matthew@gmail.com", "Adrian@gmail.com", "Mica@gmail.com", "Curtis@gmail.com", "Dan@gmail.com", "Ronn@gmail.com", "Clark@gmail.com"};
        String[] countrySuggestions = {"USA", "Canada", "Australia", "UK", "Germany", "France", "Italy", "Japan", "Brazil", "India"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countrySuggestions);
        countryCode.setAdapter(countryAdapter);
        countryCode.setThreshold(1);

        ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, emailSuggestions);
        emailAdress.setAdapter(emailAdapter);
        emailAdress.setThreshold(1);

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nameSuggestions);
        userName.setAdapter(nameAdapter);
        userName.setThreshold(1);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userName.getText().toString().isEmpty()&&!emailAdress.getText().toString().isEmpty()&& !countryCode.getText().toString().isEmpty())
                {
                    nameCheck = userName.getText().toString().trim();
                    emailCheck = emailAdress.getText().toString().trim();
                    countryCheck = countryCode.getText().toString().trim();

                    Boolean nameVal = false;
                    Boolean emailVal = false;
                    Boolean countryVal = false;

                    nameVal = Arrays.stream(nameSuggestions).anyMatch(nameCheck :: equals);
                    emailVal = Arrays.stream(emailSuggestions).anyMatch(emailCheck::equals);
                    countryVal = Arrays.stream(countrySuggestions).anyMatch(countryCheck::equals);
                    if(nameVal&&emailVal&&countryVal)
                    {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("name", nameCheck);
                        intent.putExtra("email", emailCheck);
                        intent.putExtra("country", countryCheck);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "May mali ka te", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Lagyan mo ng laman lahat", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userName.setText("");
                    emailAdress.setText("");
                    countryCode.setText("");
                    val.clear();
                }
            });




    }

    private void initCompponents() {
        userName = (AutoCompleteTextView) findViewById(R.id.eText1);
        emailAdress = (AutoCompleteTextView) findViewById(R.id.eText2);
        countryCode = (AutoCompleteTextView) findViewById(R.id.eText3);
        logIn = (Button) findViewById(R.id.logInBtn);
        clear =(TextView)findViewById(R.id.clear);
        nameCheck = userName.getText().toString();
        emailCheck = emailAdress.getText().toString();
        countryCheck = countryCode.getText().toString();
    }







}

