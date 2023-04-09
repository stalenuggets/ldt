package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ldt.databinding.ActivityLoginBinding;

/**
 * @author Erika Iwata
 * @since 4/6/23 <br>
 * Title: Project 2 <br>
 * Description: Login Activity
 */

public class LoginActivity extends AppCompatActivity {

    //declare fields
    private ActivityLoginBinding mBinding;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        //click on - back button
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

    }

    /**
     * open MainActivity
     */
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}