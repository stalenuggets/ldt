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
import com.example.ldt.db.Health;
import com.example.ldt.db.Tamadex;
import com.example.ldt.db.TamadexDao;
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
    private TamadexDao tamadexDao;

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

        //Build database
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();
        tamadexDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().tamadexDao();

        //Get shared preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String usr = sharedPref.getString("usr", "loggedOut");

        //Check if tamagotchis are added
        addTamagotchis(tamadexDao);

        //Check if predefined users are added AND user is already logged in
        addPredefinedUsers(userDao);
        skipLogin(userDao, usr);

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
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     *  Open RegisterActivity
     */
    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Open LandingActivity
     */
    public void openLandingActivity() {
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }

    /**
     * Open AdminActivity
     */
    public void openAdminActivity() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    /**
     * Check if user is already logged in
     */
    public void skipLogin(UserDao userDao, String usr) {
        //If user is NOT logged out and NOT admin
        if (usr != "loggedOut" && !userDao.findByUsername(usr).isAdmin()) {
            openLandingActivity();
        //If user is NOT logged out and is admin
        } else if (usr != "loggedOut" && userDao.findByUsername(usr).isAdmin()) {
            openAdminActivity();
        }
    }

    /**
     * Add predefined users to the database
     */
    public void addPredefinedUsers(UserDao userDao) {

        //Predefined users
        User testuser1 = new User("testuser1", "testuser1");
        User admin2 = new User("admin2", "admin2", true);

        //If predefined users are NOT in database, add them
        if (userDao.getAllUsers().isEmpty()) {
            userDao.insertUsers(testuser1, admin2);
            userDao.insertHealth(new Health(), new Health());
        }
    }

    public void addTamagotchis(TamadexDao tamadexDao) {

        //Declare tamagotchis
        Tamadex tarakotchi = new Tamadex("Tarakotchi", 34);
        Tamadex hanatchi = new Tamadex("Hanatchi", 33);
        Tamadex zuccitchi = new Tamadex("Zuccitchi", 33);

        //Check if tamagtochi is already in database
        if (tamadexDao.getAllNames().isEmpty()) {
            tamadexDao.insertTamadex(tarakotchi);
            tamadexDao.insertTamadex(hanatchi);
            tamadexDao.insertTamadex(zuccitchi);
        }
    }

}