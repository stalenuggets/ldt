package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ldt.databinding.ActivityRegisterBinding;
import com.example.ldt.db.AppDatabase;
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

                //Build database
                userDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME)
                        .allowMainThreadQueries().build().userDao();

                try {
                    //Initialize variables
                    String usr = binding.etUsr.getText().toString();
                    String pwd = binding.etPwd.getText().toString();
                    User user = userDao.findByUsername(usr);

                    //If registration is valid - proceed to LandingActivity
                    if (isValid(userDao, usr, pwd)) {
                        openLandingActivity(usr);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
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
    private void openLandingActivity(String usr) {
        //Store login info
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("usr", usr);
        editor.apply();

        //Open Landing activity
        Intent intent = new Intent(this, LandingActivity.class);
        intent.putExtra("usr", usr);
        startActivity(intent);
    }

    /**
     * Check registration info
     * @return valid or invalid
     */
    private boolean isValid(UserDao userDao, String usr, String pwd) {

        //Check for empty entries
        if (usr.isEmpty() || pwd.isEmpty()) {
            Toast.makeText(this, "Missing required fields", Toast.LENGTH_SHORT).show();
            return false;
        //Check if username is already taken
        } else if (userDao.getAllUsernames().contains(usr)) {
            Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
            return false;
        //Add user to database
        } else {
            userDao.insertUsers(new User(usr, pwd));
            return true;
        }

    }

}