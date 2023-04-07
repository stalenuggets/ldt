package com.example.ldt.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: UserDao interface for user_table
 */

@Dao
public interface UserDao {

    @Insert
    void insertAll(User... users);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();
}
