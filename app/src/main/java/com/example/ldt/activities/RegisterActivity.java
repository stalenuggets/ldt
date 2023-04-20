package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

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
import com.example.ldt.databinding.ActivityRegisterBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.Health;
import com.example.ldt.db.User;
import com.example.ldt.db.UserDao;

/**
 * @author Erika Iwata
 * @since 4/7/23 <br>
 * Title: Project 2 <br>
 * Description: Create Account Activity
 */

public class RegisterActivity extends AppCompatActivity{

    //Declare fields
    private ActivityRegisterBinding binding;
    private UserDao userDao;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
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

        //Click - sign up button
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set variables
                String usr = binding.etUsr.getText().toString();
                String pwd = binding.etPwd.getText().toString();

                //If registration is valid - proceed to LandingActivity
                if (isValid(userDao, usr, pwd)) {
                    openLandingActivity(usr, editor);
                }
            }
        });

    }

    /**
     *  Open MainActivity
     */
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Open LandingActivity
     */
    private void openLandingActivity(String usr, SharedPreferences.Editor editor) {
        //Store login info
        editor.putString("usr", usr).apply();

        //Open Landing activity
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }

    /**
     * Check registration info
     * @return valid or invalid
     */
    private boolean isValid(UserDao userDao, String usr, String pwd) {
        //Toast create
        Toast toast = new Toast(this);
        TextView tv = new TextView(this);
        Typeface font = ResourcesCompat.getFont(this, R.font.arcade_classic);

        //Customize Toast
        tv.setTypeface(font);
        tv.setTextColor(Color.rgb(210, 43, 43));
        tv.setTextSize(15);

        //Check for empty entries
        if (usr.isEmpty() || pwd.isEmpty()) {
            tv.setText("Missing required fields");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if username is already taken
        } else if (userDao.getAllUsernames().contains(usr)) {
            tv.setText("Username already taken");
            toast.setView(tv);
            toast.show();
            return false;
        //Add user to database
        } else {
            userDao.insertUsers(new User(usr, pwd));
            userDao.insertHealth(new Health());
            return true;
        }

    }

}