package com.example.ldt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

                //Build database
                userDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME)
                        .allowMainThreadQueries().build().userDao();

                try {
                    //initialize variables
                    String usr = binding.etUsr.getText().toString();
                    String pwd = binding.etPwd.getText().toString();
                    User user = userDao.findByUsername(usr);

                    //If user is valid and admin
                    if (isValid(userDao, user, usr, pwd) && user.isAdmin()) {
                        openAdminActivity();
                        //If user is valid and NOT admin
                    } else if (isValid(userDao, user, usr, pwd) && !user.isAdmin()) {
                        openLandingActivity();
                    }
                } catch (NullPointerException e){
                    e.printStackTrace();
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
    private boolean isValid(UserDao userDao, User user, String usr, String pwd) {

        //Check if username or password is empty
        if (usr.isEmpty() || pwd.isEmpty()) {
            Toast.makeText(this, "Missing required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Check if username and password match user info in database
        else if (user.getUsr().equals(usr) && user.getPwd().equals(pwd)) {
            return true;
        } else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}