package com.example.ldt.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: User class for user_table
 */

@Entity(tableName = AppDatabase.USER_TABLE, indices = {@Index(value = {"usr"}, unique = true)})
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

    @Ignore
    public User(String usr, String pwd) {
        this(usr, pwd, false);
    }

    public User(String usr, String pwd, boolean isAdmin) {
        mUsr = usr;
        mPwd = pwd;
        mIsAdmin = isAdmin;
    }

    public int getUid() {
        return mUid;
    }

    public String getUsr() {
        return mUsr;
    }

    public String getPwd() {
        return mPwd;
    }

    public boolean isAdmin() {
        return mIsAdmin;
    }

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.mIsAdmin = isAdmin;
    }

    @NonNull
    @Override
    public String toString() {
        return "uid: " + mUid + "   |   " + "Admin: " + mIsAdmin + "\nusr: " + mUsr + "\npwd: " + mPwd + "\n";
    }
}
