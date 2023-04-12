package com.example.ldt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.content.Context;
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

import com.example.ldt.R;
import com.example.ldt.databinding.ActivityLoginBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.User;
import com.example.ldt.db.UserDao;

/**
 * @author Erika Iwata
 * @since 4/6/23 <br>
 * Title: Project 2 <br>
 * Description: Login Activity
 */

public class LoginActivity extends AppCompatActivity {

    //Declare fields
    private ActivityLoginBinding binding;
    private UserDao userDao;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Build database
        userDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //Get shared preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Click - back button
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        //Click - login button
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set variables
                String usr = binding.etUsr.getText().toString();
                String pwd = binding.etPwd.getText().toString();
                User user = userDao.findByUsername(usr);

                //If user is valid and admin
                if (isValid(user, usr, pwd, editor) && user.isAdmin()) {
                    openAdminActivity();
                //If user is valid and NOT admin
                } else if (isValid(user, usr, pwd, editor) && !user.isAdmin()) {
                    openLandingActivity();
                }

            }
        });

    }

    /**
     * Open MainActivity
     */
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Open LandingActivity
     */
    private void openLandingActivity() {
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);

    }

    /**
     * Open AdminActivity
     */
    private void openAdminActivity() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    /**
     * Check login info
     * @return valid or invalid
     */
    private boolean isValid(User user, String usr, String pwd, SharedPreferences.Editor editor) {

        //Create Toast
        Toast toast = new Toast(this);
        TextView tv = new TextView(this);
        Typeface font = ResourcesCompat.getFont(this, R.font.arcade_classic);

        //Customize Toast
        tv.setTypeface(font);
        tv.setTextColor(Color.rgb(210, 43, 43));
        tv.setTextSize(15);

        //Check if username or password is empty
        if (usr.isEmpty() || pwd.isEmpty()) {
            tv.setText("Missing required fields");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if user does NOT exist or check if either username or password are incorrect
        } else if (user == null || !user.getUsr().equals(usr) || !user.getPwd().equals(pwd)) {
            tv.setText("Incorrect username or password");
            toast.setView(tv);
            toast.show();
            return false;
        } else {
            //Store login info
            editor.putString("usr", usr).apply();
            return true;
        }
    }

}