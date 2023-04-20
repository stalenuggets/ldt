package com.example.ldt.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author Erika Iwata
 * @since 4/16/23
 * Title: Project 2
 * Description: Tamadex class for tamadex table
 */

@Entity(tableName = AppDatabase.TAMADEX_TABLE)
public class Tamadex {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "rarity")
    private int mRarity;

    public Tamadex(String name, int rarity) {
        this.mName = name;
        this.mRarity = rarity;
    }
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getRarity() {
        return mRarity;
    }

    public void setRarity(int rarity) {
        this.mRarity = rarity;
    }
}
