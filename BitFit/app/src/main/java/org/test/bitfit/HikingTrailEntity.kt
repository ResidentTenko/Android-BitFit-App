package org.test.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Step 1: Create Entity
 * Data entities represent tables in your app's database.
 * Each instance(version) of an Entity data class represents a row in a table
 * for hiking trails in the app's database.
 * Essentially, we're creating a "template" for each row in our future database table,
 * by specifying the columns.
 * (This is somewhat similar to the models we used when serializing JSON!)
 */
@Entity(tableName = "hiking_trail_table")
data class HikingTrailEntity(
    @ColumnInfo(name = "trail_name") val trailName: String?,
    @ColumnInfo(name = "miles_travelled") val milesTravelled: String?,
    @ColumnInfo(name = "workout_time") val workoutTime: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)