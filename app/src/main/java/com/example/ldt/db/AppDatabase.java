package com.example.ldt.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: UserDatabase class for user_table
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Declare fields
    public static final String DB_NAME = "DB_NAME";
    public static final String USER_TABLE = "USER_TABLE";

    public abstract UserDao userDao();

}
