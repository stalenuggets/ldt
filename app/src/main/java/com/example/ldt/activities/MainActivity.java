package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
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