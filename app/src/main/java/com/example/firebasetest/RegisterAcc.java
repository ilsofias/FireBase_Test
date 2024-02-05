package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterAcc extends AppCompatActivity {
private EditText name, email, bday, age;
private Button register, seeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acc);
        name = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        bday = findViewById(R.id.b_day);
        age = findViewById(R.id.age);
        register = findViewById(R.id.register);
        seeList = findViewById(R.id.seeList);


        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterAcc.this, ClientList.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_name = name.getText().toString();
                String f_email = email.getText().toString();
                String f_bday = bday.getText().toString();
                String f_age = age.getText().toString();

                if (f_name.isEmpty()||f_age.isEmpty()||f_email.isEmpty()||f_bday.isEmpty())
                {
                    Toast.makeText(RegisterAcc.this, "Please fill out ALL information", Toast.LENGTH_SHORT).show();
                    return;
                }
                addToDataBase(f_name, f_email, f_bday, f_age);
            }
        });
    }

    private void addToDataBase(String f_name, String f_email, String f_bday, String f_age)
    {
        HashMap<String, Object> regHashmap = new HashMap<>();
        regHashmap.put("f_name", f_name);
        regHashmap.put("f_email", f_email);
        regHashmap.put("f_age", f_age);
        regHashmap.put("f_bday", f_bday);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference regRef = database.getReference("clients");

        String key = regRef.push().getKey();
        regHashmap.put("key", key);

        regRef.child(key).setValue(regHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterAcc.this, "Added as User", Toast.LENGTH_SHORT).show();
                name.getText().clear();
                age.getText().clear();
                bday.getText().clear();
                email.getText().clear();
            }
        });
    }
}