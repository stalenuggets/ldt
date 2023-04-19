package com.example.ldt.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
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

    @Insert
    void insertTamadex(Tamadex... tamadex);

    @Update
    void updateTamadex(Tamadex... tamadex);

    @Delete
    void deleteTamadex(Tamadex tamadex);

    @Query("SELECT name FROM " + AppDatabase.TAMADEX_TABLE)
    List<String> getAllNames();

}
