package com.example.ldt.db;

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

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int mUid = 0;

    @ColumnInfo(name = "usr")
    private String mUsr;

    @ColumnInfo(name = "pwd")
    private String mPwd;

    @ColumnInfo(name = "isAdmin")
    private boolean mIsAdmin;

    /**
     * Constructor with 2 param
     * @param usr username
     * @param pwd password
     */
    @Ignore
    public User(String usr, String pwd) {
        this(usr, pwd, false);
    }

    /**
     * Constructor with 3 param
     * @param usr username
     * @param pwd password
     * @param isAdmin admin status
     */
    public User(String usr, String pwd, boolean isAdmin) {
        mUsr = usr;
        mPwd = pwd;
        mIsAdmin = isAdmin;
    }

    /**
     * Getter for user id
     * @return user id
     */
    public int getUid() {
        return mUid;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsr() {
        return mUsr;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPwd() {
        return mPwd;
    }

    /**
     * Getter for admin
     * @return admin status
     */
    public boolean isAdmin() {
        return mIsAdmin;
    }

    /**
     * Setter for user id
     * @param mUid user id
     */
    public void setUid(int mUid) {
        this.mUid = mUid;
    }

    /**
     * Setter for isAdmin
     * @param mIsAdmin
     */
    public void setIsAdmin(boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }

    @Override
    public String toString() {
        return "uid: " + mUid + "   |   " + "Admin: " + mIsAdmin + "\nusr: " + mUsr + "\npwd: " + mPwd + "\n\n";
    }
}
