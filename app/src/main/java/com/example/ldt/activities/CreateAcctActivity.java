package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ldt.databinding.ActivityCreateAcctBinding;

/**
 * @author Erika Iwata
 * @since 4/7/23 <br>
 * Title: Project 2 <br>
 * Description: Create Account Activity
 */

public class CreateAcctActivity extends AppCompatActivity{

    //declare variables
    private ActivityCreateAcctBinding mBinding;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        mBinding = ActivityCreateAcctBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        //click - back button
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        //click - sign up button
        mBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    /**
     *  Open MainActivity
     */
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}