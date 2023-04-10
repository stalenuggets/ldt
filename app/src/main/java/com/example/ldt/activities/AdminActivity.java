package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ldt.databinding.ActivityAdminBinding;

/**
 * @author Erika Iwata
 * @since 4/9/23 <br>
 * Title: Project 2 <br>
 * Description: Admin Landing Page Activity
 */

public class AdminActivity extends AppCompatActivity {

    //Declare fields
    private ActivityAdminBinding binding;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        View view = binding.getRoot();
        setContentView(view);

    }
}