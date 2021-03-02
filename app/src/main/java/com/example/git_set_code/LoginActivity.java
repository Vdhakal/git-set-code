package com.example.git_set_code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeUIelements();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ONCLICK","DONE");
                Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
                LoginActivity.this.startActivity(intent);

            }
        });

    }

    private void initializeUIelements() {
        login_button = findViewById(R.id.login_button);
    }

}