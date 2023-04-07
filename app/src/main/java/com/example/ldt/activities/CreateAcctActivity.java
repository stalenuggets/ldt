package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.ldt.R;

public class CreateAcctActivity extends AppCompatActivity {

    //declare varaibles
    private EditText mEtUsr;
    private EditText mEtPwd;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);

        //set variables
        mEtUsr = findViewById(R.id.et_usr);
        mEtPwd = findViewById(R.id.et_pwd);
        mBtnRegister = findViewById(R.id.btn_register);
    }
}