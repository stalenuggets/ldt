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
    private int mUid;

    @ColumnInfo(name = "usr")
    private String mUsr;

    @ColumnInfo(name = "pwd")
    private String mPwd;

    @ColumnInfo(name = "isAdmin")
    private boolean mIsAdmin;

    /**
     * constructor with 2 param
     * @param usr username
     * @param pwd password
     */
    @Ignore
    public User(String usr, String pwd) {
        this(usr, pwd, false);
    }

    /**
     * constructor with 3 param
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
     * getter for user id
     * @return user id
     */
    public int getUid() {
        return mUid;
    }

    /**
     * getter for username
     * @return username
     */
    public String getUsr() {
        return mUsr;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPwd() {
        return mPwd;
    }

    /**
     * getter for admin
     * @return admin status
     */
    public boolean getIsAdmin() {
        return mIsAdmin;
    }

    /**
     * setter for user id
     * @param mUid user id
     */
    public void setUid(int mUid) {
        this.mUid = mUid;
    }

    /**
     * setter for username
     * @param mUsr username
     */
    public void setUsr(String mUsr) {
        this.mUsr = mUsr;
    }

    /**
     * setter for password
     * @param mPwd password
     */
    public void setPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    /**
     * setter for isAdmin
     * @param mIsAdmin isAdmin
     */
    public void setIsAdmin(boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }

    /**
     * toString for USER_TABLE
     * @return USER_TABLE contents
     */
    @Override
    public String toString() {
        return "User{" +
                "mUid=" + mUid +
                ", mUsr='" + mUsr + '\'' +
                ", mPwd='" + mPwd + '\'' +
                ", mIsAdmin=" + mIsAdmin +
                '}';
    }

}
