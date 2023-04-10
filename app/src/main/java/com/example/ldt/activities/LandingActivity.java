package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ldt.databinding.ActivityLandingBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.UserDao;

/**
 * @author Erika Iwata
 * @since 4/9/23 <br>
 * Title: Project 2 <br>
 * Description: Landing Page Activity
 */

public class LandingActivity extends AppCompatActivity {

    //Declare Fields
    private ActivityLandingBinding binding;
    private UserDao userDao;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Build database
        userDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //Display username
        TextView usr = (TextView) binding.tvUsr;
        usr.setText("User: " + getIntent().getStringExtra("usr"));

        //Click - start game button
        binding.btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

    }

    /**
     * Open HomeActivity
     */
    private void openHomeActivity() {
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
    }

}