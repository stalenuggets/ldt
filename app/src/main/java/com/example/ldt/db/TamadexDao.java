package com.example.ldt.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author Erika Iwata
 * @since 4/16/23
 * Title: Project 2
 * Description: TamadexDao interface for tamadex table
 */

@Dao
public interface TamadexDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTamadex(Tamadex... tamadex);

    @Update
    void updateTamadex(Tamadex... tamadex);

    @Delete
    void deleteTamadex(Tamadex tamadex);

    @Query("SELECT name FROM " + AppDatabase.TAMADEX_TABLE + " ORDER BY rarity ASC")
    List<String> getAllNames();

    @Query("SELECT rarity FROM " + AppDatabase.TAMADEX_TABLE + " ORDER BY rarity ASC")
    List<Integer> getAllRarities();

    @Query("SELECT * FROM " + AppDatabase.TAMADEX_TABLE + " WHERE name = :name")
    Tamadex findByName(String name);

}
