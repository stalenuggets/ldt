package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ldt.R;
import com.example.ldt.user.User;
import com.example.ldt.user.UserDatabase;
import com.example.ldt.databinding.ActivityLoginBinding;

import java.util.List;

/**
 * @author Erika Iwata
 * @since 4/6/23 <br>
 * Title: Project 2 <br>
 * Description: Login Activity
 */

public class LoginActivity extends AppCompatActivity {

    //declare fields
    private EditText mEtUsr;
    private EditText mEtPwd;
    private Button mBtnLogin;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set variables
        mEtUsr = findViewById(R.id.et_usr);
        mEtPwd = findViewById(R.id.et_pwd);
        mBtnLogin = findViewById(R.id.btn_login);

        //build user database
        UserDatabase userDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user-database").allowMainThreadQueries().build();

        //insert predefined users
        User testuser1 = new User("testuser1", "testuser1");
        User admin2 = new User("admin2", "admin2", true);
        userDb.userDao().insertAll(testuser1, admin2);

        //check if predefined users are properly stored in database
        List<User> userList = userDb.userDao().getAllUsers();
        for (User user: userList) {
            Log.d("users", user.usr + " " + user.pwd + " " + user.admin);
        }

    }

    //validate username and password
    private void validate(String username, String password) {

    }
}