package com.example.root.ngopi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Register extends AppCompatActivity {
    EditText email, pass;
    Button login, regis;
    String user_email, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        regis = (Button) findViewById(R.id.regis);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = email.getText().toString();
                user_pass = pass.getText().toString();

                Register(user_email, user_pass);
                findAll();
            }
        });
    }

    private void Register(String email, String pass) {
        AppDatabase db = AppDatabase.getAppDatabase(this);
        User user = new User();
        user.setFirstName(email);
        user.setPassword(pass);

        db.userDao().insertAll(user);

        Log.d("SUCCESS => ", "Data has been inserted");
    }

    private void findAll()
    {
        AppDatabase db = AppDatabase.getAppDatabase(this);
        List<User> user = db.userDao().getAll();

        for (User u : user)
        {
            Log.d("VIEW USER => ", "EMAIL : "+u.getFirstName() + " - PASS : " +u.getPassword());
        }
    }
}
