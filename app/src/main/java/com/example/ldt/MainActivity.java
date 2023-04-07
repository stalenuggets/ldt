package com.example.ldt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ldt.databinding.ActivityMainBinding;
import com.example.ldt.ui.login.LoginActivity;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: Landing Page 1
 */

public class MainActivity extends AppCompatActivity {

    //declare fields
    private Button mLoginButton;
    private TextView mCreateAnAccount;
    private ActivityMainBinding mMainBinding;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mMainBinding.getRoot();

        //set variables
        mLoginButton = mMainBinding.loginButton;
        mCreateAnAccount = mMainBinding.createAnAccount;

        //click on login button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        //click on create an account
        mCreateAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

    }

    //openLoginActivity method
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}