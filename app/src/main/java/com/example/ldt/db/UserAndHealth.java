package com.example.ldt.db;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * @author Erika Iwata
 * @since 4/17/23
 * Title: Project 2
 * Description: Tells program that User and Health tables have a 1-1 relationship
 */

public class UserAndHealth {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "uid",
            entityColumn = "uid"
    )
    public Health health;
}
