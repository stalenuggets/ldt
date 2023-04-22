package com.example.ldt.db;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

/**
 * @author Erika Iwata
 * @since 4/16/23
 * Title: Project 2
 * Description: Health class for health_table
 */

@Entity(tableName = AppDatabase.HEALTH_TABLE)
public class Health{

    //Linked to uid from user table
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int mUid = 0;

    @ColumnInfo(name = "name")
    private String mName = "Egg";

    @ColumnInfo(name = "hungry")
    private int mHungry = 4;

    @ColumnInfo(name = "happy")
    private int mHappy = 4;

    @ColumnInfo(name = "discipline")
    private int mDiscipline = 4;

    @ColumnInfo(name = "isAsleep")
    private boolean mIsAsleep = false;

    @ColumnInfo(name = "isMisbehaving")
    private boolean mIsMisbehaving = false;

    public int getUid() {
        return mUid;
    }

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getHungry() {
        return mHungry;
    }

    public void setHungry(int hungry) {
        this.mHungry = hungry;
    }

    public int getHappy() {
        return mHappy;
    }

    public void setHappy(int happy) {
        this.mHappy = happy;
    }

    public int getDiscipline() {
        return mDiscipline;
    }

    public void setDiscipline(int discipline) {
        this.mDiscipline = discipline;
    }

    public boolean isAsleep() {
        return mIsAsleep;
    }

    public void setIsAsleep(boolean isAsleep) {
        this.mIsAsleep = isAsleep;
    }

    public boolean isMisbehaving() {
        return mIsMisbehaving;
    }

    public void setIsMisbehaving(boolean isMisbehaving) {
        this.mIsMisbehaving = isMisbehaving;
    }

    public String toString() {
        return getName() + "\n";
    }

}
