package com.example.root.ngopi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    String email, pass;

    TextView tv_email, tv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("SESSION", MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);
        pass = sharedPreferences.getString("pass", null);

        tv_email = (TextView) findViewById(R.id.email);
        tv_email.setText(email);
    }
}
