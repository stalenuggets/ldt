package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ldt.databinding.ActivityMainBinding;

/**
 * @author Erika Iwata
 * @since 4/6/23 <br>
 * Title: Project 2 <br>
 * Description: Landing Page 1
 */

public class MainActivity extends AppCompatActivity {

    //declare fields
    private ActivityMainBinding mBinding;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        //click - login button
        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        //click - create an account button
        mBinding.btnCreateAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateAcctActivity();
            }
        });

    }

    /**
     *  Open LoginActivity
     */
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     *  Open CreateAcctActivity
     */
    public void openCreateAcctActivity() {
        Intent intent = new Intent(this, CreateAcctActivity.class);
        startActivity(intent);
    }

}