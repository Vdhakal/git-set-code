package com.example.git_set_code.activities;

/**
 * Importing necessary packages
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.permissions.PermissionsRequestor;

/**
 *
 */
public class LoginActivity extends AppCompatActivity {

    //Declaring variables
    Button login_button;
    Animation topAnim, bottomAnim;
    ImageView image;
    Button button;
    private PermissionsRequestor permissionsRequestor;

    @Override
    /**
     * Initializes the activity LoginActivity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        image.setAnimation(topAnim);
        button = findViewById(R.id.login_button);
        button.setAnimation(bottomAnim);

        initializeUIelements();

        handleAndroidPermissions();
        login_button.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Log.d("ONCLICK", "DONE");
                Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
                LoginActivity.this.startActivity(intent);

            }
        });

    }

    /**
     * Handles the permissions
     */
    private void handleAndroidPermissions() {
        permissionsRequestor = new PermissionsRequestor(LoginActivity.this);
        permissionsRequestor.request(new PermissionsRequestor.ResultListener() {

            @Override
            public void permissionsGranted() {
            }

            @Override
            public void permissionsDenied() {
                Log.e("TAG", "Permissions denied by user.");
                Toast.makeText(LoginActivity.this, "Please allow permissions to use this app", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Initializes the User Interface Element
     */
    private void initializeUIelements() {
        login_button = findViewById(R.id.login_button);
    }

}