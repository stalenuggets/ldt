package com.example.ldt.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ldt.R;
import com.example.ldt.databinding.ActivityHomeBinding;
import com.example.ldt.databinding.MenuDeleteConfirmBinding;
import com.example.ldt.databinding.MenuExitBinding;
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

        //TODO
        Log.d("home", "onCreate");

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

                //TODO
                Log.d("home", "back btn");

                //If user is admin
                if (userDao.findByUsername(usr).isAdmin()) {
                    openAdminActivity(usr);
                } else {
                    openLandingActivity(usr);
                }

            }
        });

        //Click - Exit button
        binding.ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO
                Log.d("home", "exit btn");

                openExitMenu(userDao, usr, editor);
            }
        });

    }

    /**
     * Open Exit Menu
     */
    public void openExitMenu(UserDao userDao, String usr, SharedPreferences.Editor editor) {

        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        MenuExitBinding binding = MenuExitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize Dialog popup
        dialogBuilder.setView(view);
        Dialog exitDialog = dialogBuilder.create();
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.show();

        //Click - Close Window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
            }
        });

        //Click - quit game button
        binding.btnQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If user is admin
                if (userDao.findByUsername(usr).isAdmin()) {
                    openAdminActivity(usr);
                } else {
                    openLandingActivity(usr);
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
                openDeleteConfirm(exitDialog, userDao, usr, editor);
            }
        });
    }

    /**
     * Open Delete confirm menu
     */
    public void openDeleteConfirm(Dialog exitDialog, UserDao userDao, String usr, SharedPreferences.Editor editor) {

        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        MenuDeleteConfirmBinding binding = MenuDeleteConfirmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog deleteDialog = dialogBuilder.create();
        deleteDialog.setCanceledOnTouchOutside(false);
        deleteDialog.show();

        //Click - Close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog.dismiss();
                exitDialog.dismiss();
            }
        });

        //Click - Yes button
        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete user
                logout(editor);
                userDao.deleteUser(userDao.findByUsername(usr));

                openMainActivity();
            }
        });

        //Click - No button
        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Close both dialog menus
                deleteDialog.dismiss();
                exitDialog.dismiss();
            }
        });
    }

    /**
     * Open LandingActivity
     */
    public void openLandingActivity(String usr) {
        Intent intent = new Intent(this, LandingActivity.class);
        intent.putExtra("usr", usr);
        startActivity(intent);
    }

    /**
     * Logout current user
     */
    public void logout(SharedPreferences.Editor editor) {
        editor.clear().apply();
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
    public void openAdminActivity(String usr) {
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("usr", usr);
        startActivity(intent);
    }

}
