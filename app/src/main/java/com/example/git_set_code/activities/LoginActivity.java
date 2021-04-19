package com.example.git_set_code.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.git_set_code.R;

public class LoginActivity extends AppCompatActivity {

    Button login_button;
    Animation topAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        image.setAnimation(topAnim);

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