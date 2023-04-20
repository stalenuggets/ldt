package com.example.ldt.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: UserDao interface for user_table and health_table
 */

@Dao
public interface UserDao {

    //-----------------------------USER-------------------------------

    @Insert
    void insertUsers(User... user);

    @Update
    void updateUsers(User... user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT usr FROM " + AppDatabase.USER_TABLE)
    public List<String> getAllUsernames();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE usr = :usr")
    User findByUsername(String usr);

    //-----------------------------HEALTH-------------------------------

    @Insert
    void insertHealth(Health... health);

    @Update
    void updateHealth(Health... health);

    @Delete
    void deleteHealth(Health health);

    @Query("SELECT * FROM " + AppDatabase.HEALTH_TABLE + " WHERE uid = :uid")
    Health findById(int uid);

}



