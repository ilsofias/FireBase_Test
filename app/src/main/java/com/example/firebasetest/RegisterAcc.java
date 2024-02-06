package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class RegisterAcc extends AppCompatActivity {
private EditText name, email, bday, age;
private Button register, seeList;
private CoordinatorLayout coorLayout;

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
        coorLayout = findViewById(R.id.coorLayout);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_snackBar();
            }
        });

        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_toast();
                Intent intent = new Intent(RegisterAcc.this, ClientList.class);
                startActivity(intent);
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
                //Toast.makeText(RegisterAcc.this, "Successfully Added User", Toast.LENGTH_SHORT).show();

                name.getText().clear();
                age.getText().clear();
                bday.getText().clear();
                email.getText().clear();
            }
        });
    }

    private void c_toast()
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout= inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.customToast) );
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private void c_snackBar(){
        final Snackbar snackbar = Snackbar.make(coorLayout, "", Snackbar.LENGTH_LONG);
        View customSnackView = getLayoutInflater().inflate(R.layout.custom_snackbar, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        Button bGotoWebsite = (Button)customSnackView.findViewById(R.id.yes);
        bGotoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_name = name.getText().toString();
                String f_email = email.getText().toString();
                String f_bday = bday.getText().toString();
                String f_age = age.getText().toString();
                if (f_name.isEmpty()||f_age.isEmpty()||f_email.isEmpty()||f_bday.isEmpty())
                {
                    return;
                }
                addToDataBase(f_name, f_email, f_bday, f_age);
                Toast.makeText(getApplicationContext(), "Adding User", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);
        snackbar.show();
    }

}
