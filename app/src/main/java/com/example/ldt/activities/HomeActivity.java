package com.example.ldt.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ldt.R;
import com.example.ldt.databinding.ActivityHomeBinding;
import com.example.ldt.databinding.MenuDeleteConfirmBinding;
import com.example.ldt.databinding.MenuExitBinding;
import com.example.ldt.db.UserDao;

/**
 * @author Erika Iwata
 * @since 4/10/23 <br>
 * Title: Project 2 <br>
 * Description: Home Page Activity
 */

public class HomeActivity extends AppCompatActivity {

    //Declare fields
    private ActivityHomeBinding binding;
    private UserDao userDao;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExitMenu();
            }
        });

    }

    /**
     * Open Exit Menu
     */
    public void openExitMenu() {

        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        MenuExitBinding binding = MenuExitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        dialogBuilder.setView(view);
        dialogBuilder.create().show();

        //Click - quit game button
        binding.btnQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLandingActivity();
            }
        });

        //Click - logout button
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                openMainActivity();
            }
        });

        //Click - delete account
        binding.btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleteConfirm();
            }
        });
    }

    /**
     * Open Delete confirm menu
     */
    public void openDeleteConfirm() {

        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        MenuDeleteConfirmBinding binding = MenuDeleteConfirmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        dialogBuilder.setView(view);
        dialogBuilder.create().show();

        //Click - Yes button
        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get username
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                String usr = sharedPref.getString("usr", "");

                //delete user
                userDao.deleteUser(userDao.findByUsername(usr));
                editor.clear().apply();

                openMainActivity();
            }
        });

        //Click - No button
        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Close menu
                dialogBuilder.create().dismiss();
            }
        });
    }

    /**
     * Open LandingActivity
     */
    public void openLandingActivity() {
        Intent intent = new Intent(this, LandingActivity.class);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        intent.putExtra("usr", sharedPref.getString("usr", ""));
        startActivity(intent);
    }

    /**
     * Logout current user
     */
    public void logout() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().apply();
    }

    /**
     * Open MainActivity
     */
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
