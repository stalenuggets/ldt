package com.example.ldt.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: User class for user_table
 */

//user class
@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "usr")
    public String usr;

    @ColumnInfo(name = "pwd")
    public String pwd;

    @ColumnInfo(name = "admin")
    public boolean admin;

    //constructor - 2 param
    @Ignore
    public User(String usr, String pwd) {
        this(usr, pwd, false);
    }

    //constructor - 3 param
    public User(String usr, String pwd, boolean admin) {
        this.usr = usr;
        this.pwd = pwd;
        this.admin = admin;
    }

}
