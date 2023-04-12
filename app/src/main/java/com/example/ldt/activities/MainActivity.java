package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.example.ldt.databinding.ActivityMainBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.User;
import com.example.ldt.db.UserDao;
import com.google.android.material.tabs.TabLayout;

/**
 * @author Erika Iwata
 * @since 4/6/23 <br>
 * Title: Project 2 <br>
 * Description: Landing Page 1
 */

public class MainActivity extends AppCompatActivity {

    //Declare fields
    private ActivityMainBinding binding;
    private UserDao userDao;
    public static final String SHARED_PREFS = "sharedPrefs";

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addPredefinedUsers();
        skipLogin();

        //Click - login button
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        //Click - create an account button
        binding.btnCreateAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

    }

    /**
     *  Open LoginActivity
     */
    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     *  Open RegisterActivity
     */
    private void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Check if user is already logged in
     */
    public void skipLogin() {

        //Get user preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String usr = sharedPref.getString("usr", "loggedOut");

        //Build database
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //If user is NOT logged out and NOT admin
        if (usr != "loggedOut" && !userDao.findByUsername(usr).isAdmin()) {
            Intent intent = new Intent(this, LandingActivity.class);
            intent.putExtra("usr", usr);
            startActivity(intent);
        //If user is NOT logged out and is admin
        } else if (usr != "loggedOut" && userDao.findByUsername(usr).isAdmin()) {

        }
    }

    /**
     * Add predefined users to the database
     */
    private void addPredefinedUsers() {
        //Build database
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //Predefined users
        User testuser1 = new User("testuser1", "testuser1");
        User admin2 = new User("admin2", "admin2", true);

        //If predefined users are NOT in database, add them
        if (userDao.findByUsername("testuser1") == null && userDao.findByUsername("admin2") == null) {
            userDao.insertUsers(testuser1, admin2);
        }

    }

}