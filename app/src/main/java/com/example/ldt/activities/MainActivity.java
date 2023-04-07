package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ldt.R;
import com.example.ldt.databinding.ActivityMainBinding;

/**
 * @author Erika Iwata
 * @since 4/6/23 <br>
 * Title: Project 2 <br>
 * Description: Landing Page 1
 */

public class MainActivity extends AppCompatActivity {

    //declare fields
    private Button mBtnLogin;
    private Button mBtnCreateAcct;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set variables
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnCreateAcct = findViewById(R.id.btn_create_acct);

        //click on - login button
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        //click on - create an account button
        mBtnCreateAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateAcctActivity();
            }
        });

    }

    //openLoginActivity method
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //openCreateAcctActivity method
    public void openCreateAcctActivity() {
        Intent intent = new Intent(this, CreateAcctActivity.class);
        startActivity(intent);
    }

}