package com.example.ldt.user;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: UserDatabase class for user_table
 */

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    //userDao method
    public abstract UserDao userDao();

}
