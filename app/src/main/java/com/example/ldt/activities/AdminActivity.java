package com.example.ldt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldt.R;
import com.example.ldt.databinding.ActivityAdminBinding;
import com.example.ldt.databinding.ActivityMainBinding;
import com.example.ldt.databinding.DialogAddAdminBinding;
import com.example.ldt.databinding.DialogChooseFromExistingBinding;
import com.example.ldt.databinding.DialogCreateNewAdminBinding;
import com.example.ldt.databinding.DialogDeleteAcctConfirmBinding;
import com.example.ldt.databinding.DialogDeleteUserBinding;
import com.example.ldt.databinding.DialogManageUsersBinding;
import com.example.ldt.databinding.DialogUserInfoBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.User;
import com.example.ldt.db.UserDao;

import org.w3c.dom.Text;

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

        //Get shared preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        String usr = sharedPref.getString("usr", "");

        //Display username
        TextView username = (TextView) binding.tvUsr;
        String usrDisplay = "<font color=#000000>User: </font> <font color=#4169E1>" + sharedPref.getString("usr", "loggedOut") + "</font>";
        username.setText(Html.fromHtml(usrDisplay));

        //Click - start game button
        binding.btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        //Click - manage existing users button
        binding.btnManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openManageUsersDialog(userDao, editor);
            }
        });

    }

    /**
     * Logout current user
     */
    public void logout(SharedPreferences.Editor editor) {
        editor.clear().apply();
    }

    /**
     * Open Home Activity
     */
    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * Open Manage Users Dialog
     */
    public void openManageUsersDialog(UserDao userDao, SharedPreferences.Editor editor) {
        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogManageUsersBinding binding = DialogManageUsersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - Close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Click - Add admin user
        binding.btnAddAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openAddAdminDialog(userDao);
            }
        });

        //Click - Delete user
        binding.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openDeleteUserDialog(userDao, editor);
            }
        });

        //Click - Look up user info
        binding.btnLookUpUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openLookUpUserDialog(userDao);
            }
        });
    }

    /**
     * Open Look Up User Info Dialog
     */
    public void openLookUpUserDialog(UserDao userDao) {
        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogUserInfoBinding binding = DialogUserInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Read from userDao
        String userInfo = "";
        for (User user: userDao.getAllUsers()) {
            userInfo += user.toString();
        }

        //Set new text view
        TextView tvUserInfo = (TextView) binding.tvUserInfo;
        tvUserInfo.setText(userInfo);

        //Click - Close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /**
     * Open Add Admin User Dialog
     */
    public void openAddAdminDialog(UserDao userDao) {
        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogAddAdminBinding binding = DialogAddAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - Close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Click - Create new user
        binding.btnCreateNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openAddNewAdminDialog(userDao);
            }
        });

        //Click - Choose from existing
        binding.btnChooseFromExisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openChooseFromExistingDialog(userDao);
            }
        });
    }

    /**
     * Open Add New Admin Dialog
     */
    public void openAddNewAdminDialog(UserDao userDao) {
        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogCreateNewAdminBinding binding = DialogCreateNewAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - Close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        binding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set variables
                String usr = binding.etUsr.getText().toString();
                String pwd = binding.etPwd.getText().toString();

                //If registration is valid - proceed to LandingActivity
                if (isValidRegister(userDao, usr, pwd)) {
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * Open Choose From Existing Dialog
     */
    public void openChooseFromExistingDialog(UserDao userDao) {
        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogChooseFromExistingBinding binding = DialogChooseFromExistingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Click - Add Admin
        binding.btnAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set variables
                String usr = binding.etUsr.getText().toString();

                //Check if user is okay to add
                if (isValidUsername(usr, userDao)) {
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * Open Delete User Dialog
     */
    public void openDeleteUserDialog(UserDao userDao, SharedPreferences.Editor editor) {
        //Open Dialog setup
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        DialogDeleteUserBinding binding = DialogDeleteUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        //Customize dialog popup
        dialogBuilder.setView(view);
        Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Click - close window
        binding.btnCloseWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Click - delete
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set variables
                String usr = binding.etUsr.getText().toString();

                if (isValidDeleteUser(userDao, usr, editor)) {
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * Check login info
     * @return valid or invalid
     */
    private boolean isValidUsername(String usr, UserDao userDao) {

        //Create Toast
        Toast toast = new Toast(this);
        TextView tv = new TextView(this);
        Typeface font = ResourcesCompat.getFont(this, R.font.arcade_classic);

        //Customize Toast
        tv.setTypeface(font);
        tv.setTextColor(Color.rgb(210, 43, 43));
        tv.setTextSize(15);

        //set variable
        User user = userDao.findByUsername(usr);

        //Check if username is empty
        if (usr.isEmpty()) {
            tv.setText("Missing required field");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if user does NOT exist
        } else if (user == null) {
            tv.setText("Invalid username");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if user is already admin
        } else if (user.isAdmin()) {
            tv.setText("Already admin");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if predefined user
        }else if (usr.equals("testuser1")) {
            tv.setText("Predefined user");
            toast.setView(tv);
            toast.show();
            return false;
        } else {
            user.setIsAdmin(true);
            userDao.updateUsers(user);
            tv.setTextColor(Color.rgb(60,179,113));
            tv.setText("New Admin User");
            toast.setView(tv);
            toast.show();
            return true;
        }
    }

    /**
     * Check registration info
     * @return valid or invalid
     */
    private boolean isValidRegister(UserDao userDao, String usr, String pwd) {
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
            userDao.insertUsers(new User(usr, pwd, true));
            tv.setTextColor(Color.rgb(60,179,113));
            tv.setText("New User Added");
            toast.setView(tv);
            toast.show();
            return true;
        }

    }

    /**
     * Check if user is okay to delete
     * @return valid or invalid
     */
    public boolean isValidDeleteUser(UserDao userDao, String usr, SharedPreferences.Editor editor) {
        //Toast create
        Toast toast = new Toast(this);
        TextView tv = new TextView(this);
        Typeface font = ResourcesCompat.getFont(this, R.font.arcade_classic);

        //Customize Toast
        tv.setTypeface(font);
        tv.setTextColor(Color.rgb(210, 43, 43));
        tv.setTextSize(15);

        //Check for empty entries
        if (usr.isEmpty()) {
            tv.setText("Missing required fields");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if predefined user
        }else if (usr.equals("testuser1") || usr.equals("admin2")) {
            tv.setText("Predefined user");
            toast.setView(tv);
            toast.show();
            return false;
        //Check if user does NOT exist
        } else if (userDao.findByUsername(usr) == null) {
            tv.setText("Invalid username");
            toast.setView(tv);
            toast.show();
            return false;
        } else {
            //Delete user
            logout(editor);
            userDao.deleteUser(userDao.findByUsername(usr));
            //Set message
            tv.setTextColor(Color.rgb(60, 179, 113));
            tv.setText("Account Deleted");
            toast.setView(tv);
            toast.show();
            return true;
        }
    }

}