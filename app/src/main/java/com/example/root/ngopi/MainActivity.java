package com.example.root.ngopi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText email, pass;
    Button login, regis;
    String user_email, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        regis = (Button) findViewById(R.id.regis);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                finish();
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = email.getText().toString();
                user_pass = pass.getText().toString();
                findUser(user_email, user_pass);
            }
        });

    }

    private void findUser(String email, String pass)
    {
        AppDatabase db = AppDatabase.getAppDatabase(this);
        User user = db.userDao().findByName(email, pass);

        try
        {
            SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
            editor.putString("email", user.getFirstName());
            editor.putString("pass", user.getPassword());
            editor.apply();

            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
            finish();

            Log.d("RESULT LOGIN =>", user.getFirstName() +" - "+user.getPassword());
        }
        catch (Exception e)
        {
            Log.d("ERROR MSG => ", e.getMessage());
        }
    }
}
