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
 * Description: UserDao interface for user_table
 */

@Dao
public interface UserDao {

    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " ORDER BY mUid ASC")
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUid = :uid")
    User getUserById(int uid);

}
