package com.example.ldt.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import com.example.ldt.R;
import com.example.ldt.databinding.ActivityHomeBinding;
import com.example.ldt.databinding.DialogDeleteAcctConfirmBinding;
import com.example.ldt.databinding.DialogExitGameBinding;
import com.example.ldt.db.AppDatabase;
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
//    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Build database
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //Get username
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        String usr = sharedPref.getString("usr", "");

        //Click - Back button
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If user is admin
                if (userDao.findByUsername(usr).isAdmin()) {
                    openAdminActivity();
                } else {
                    openLandingActivity();
                }

            }
        });

        //Click - Exit button
        binding.ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExitGameDialog(userDao, usr, editor);
            }
        });

    } //End onCreate

    /**
     * Open Exit Menu
     */
    public void openExitGameDialog(UserDao userDao, String usr, SharedPreferences.Editor editor) {

        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogExitGameBinding binding = DialogExitGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize Dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - Close Window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Click - quit game button
        binding.btnQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If user is admin
                if (userDao.findByUsername(usr).isAdmin()) {
                    openAdminActivity();
                } else {
                    openLandingActivity();
                }
            }
        });

        //Click - logout button
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(editor);
                openMainActivity();
            }
        });

        //Click - delete account
        binding.btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openDeleteAcctDialog(userDao, usr, editor);
            }
        });

    } //End openExitMenu

    /**
     * Open Delete confirm menu
     */
    public void openDeleteAcctDialog(UserDao userDao, String usr, SharedPreferences.Editor editor) {

        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogDeleteAcctConfirmBinding binding = DialogDeleteAcctConfirmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - Close window button
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Click - Yes button
        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if user is okay to delete
                if (deleteuserCheck(usr, userDao, editor)) {
                    openMainActivity();
                }

            }
        });

        //Click - No button
        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /**
     * Check if it's okay to delete this user
     */
    public boolean deleteuserCheck(String usr, UserDao userDao, SharedPreferences.Editor editor) {

        //Create Toast
        Toast toast = new Toast(this);
        TextView tv = new TextView(this);
        Typeface font = ResourcesCompat.getFont(this, R.font.arcade_classic);

        //Customize Toast
        tv.setTypeface(font);
        tv.setTextColor(Color.rgb(210, 43, 43));
        tv.setTextSize(15);

        //Check if predefined user
        if (usr.equals("testuser1") || usr.equals("admin2")) {
            tv.setText("Predefined user");
            toast.setView(tv);
            toast.show();
            return false;
        } else {
            //Delete user
            logout(editor);
            userDao.deleteUser(userDao.findByUsername(usr));
            //Set message
            tv.setTextColor(Color.rgb(60, 179, 113));
            tv.setText("Account Deleted");
            toast.setView(tv);
            toast.show();
            return true;
        }
    }

    /**
     * Logout current user
     */
    public void logout(SharedPreferences.Editor editor) {
        editor.clear().apply();
    }

    /**
     * Open LandingActivity
     */
    public void openLandingActivity() {
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }

    /**
     * Open MainActivity
     */
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Open AdminActivity
     */
    public void openAdminActivity() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

}
