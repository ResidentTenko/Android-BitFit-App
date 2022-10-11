package org.test.bitfit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Step 3: Create Database
 * The following code defines an AppDatabase class to hold the database.
 * AppDatabase defines the database configuration and serves as the app's main access point
 * to the persisted data.
 * The database class must satisfy the following conditions:
 * The class must be annotated with a @Database annotation that includes an entities array
 * that lists all of the data entities associated with the database.
 * In our case, just the HikingTrailEntity type.
 * The class must be an abstract class that extends RoomDatabase.
 * For each DAO class that is associated with the database,
 * the database class must define an abstract method that has zero arguments
 * and returns an instance of the DAO class.
 */

// entities array (in this case we only have one entity for this database)
@Database(entities = [HikingTrailEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    // abstract method with zero arguments that returns an instance of DAO class
    abstract fun hikingTrailDao():HikingTrailDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "HikingTrail-db"
            ).build()
    }
}