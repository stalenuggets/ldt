package com.example.ldt.db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author Erika Iwata
 * @since 4/6/23
 * Title: Project 2
 * Description: App database
 */

@Database(entities = {User.class, Health.class, Tamadex.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Declare fields
    public static final String DB_NAME = "db_name";
    public static final String HEALTH_TABLE = "health_table";
    public static final String TAMADEX_TABLE = "tamadex_table";
    public static final String USER_TABLE = "user_table";

    //Abstract methods for DAO
    public abstract TamadexDao tamadexDao();
    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;

    // Singleton pattern to ensure only one instance of the database is created
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
