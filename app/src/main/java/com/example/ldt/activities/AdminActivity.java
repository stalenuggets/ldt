package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ldt.databinding.ActivityAdminBinding;
import com.example.ldt.databinding.ActivityMainBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.UserDao;

/**
 * @author Erika Iwata
 * @since 4/9/23 <br>
 * Title: Project 2 <br>
 * Description: Admin Landing Page Activity
 */

public class AdminActivity extends AppCompatActivity {

    //Declare fields
    private ActivityAdminBinding binding;
    private UserDao userDao;

    /**
     * Tells program what to do when this activity is created
     * @param savedInstanceState saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate setup
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Build database
        userDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //Display username
        TextView usr = (TextView) binding.tvUsr;
        usr.setText("User: " + getIntent().getStringExtra("usr"));

        //Click - back button
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
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
}