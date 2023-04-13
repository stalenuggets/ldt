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
    void insertUsers(User... user);

    @Update
    void updateUsers(User... user);

    @Delete
    void deleteUser(User user);

    /**
     * get all user id
     * @return all user id
     */
    @Query("SELECT uid FROM " + AppDatabase.USER_TABLE)
    public List<Integer> getAllUid();

    /**
     * Get all usernames
     * @return all usernames
     */
    @Query("SELECT usr FROM " + AppDatabase.USER_TABLE)
    public List<String> getAllUsernames();

    /**
     * Get all isAdmin
     * @return all isAdmin
     */
    @Query("SELECT isAdmin FROM " + AppDatabase.USER_TABLE)
    public List<Boolean> getAllIsAdmin();

    /**
     * get user by user id
     * @param uid user id
     * @return user
     */
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE uid = :uid")
    User findByUid(int uid);

    /**
     * Get user by username
     * @param usr username
     * @return user
     */
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE usr = :usr")
    User findByUsername(String usr);

    /**
     * Get all users
     * @return all users
     */
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

}



