package org.test.bitfit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Step 2: Create DAOs
 * Data access objects (DAOs) defines and provides methods that your app can use to query,
 * update, insert, and delete data in our created database.
 * Think of the DAO as a messenger who goes between your app and the database.
 * With Room, we'll represent the DAO using an Interface.
 * What functions will we need for our application?
 * Once we fetch data from the TrailDetailActivity, we will need to insert new data into our table.
 * We will need a way to get all of those entries from the database to display.
 * We don't want to cache things in the database that are stale, so every time we get fresh data,
 * we will need to clear out all older entries.
 * So we need a insert, a get, and a delete function.
 */
@Dao
interface HikingTrailDao {
    // query table declared in HikingTrailEntity.kt
    // get all entries in the database
    @Query("SELECT * FROM hiking_trail_table")
    fun getAll(): Flow<List<HikingTrailEntity>>
    // insert just one hiking trail
    @Insert
    fun insert(hikingTrail: HikingTrailEntity)
    // insert a list of hiking trails
    @Delete
    fun delete(hikingTrail: HikingTrailEntity)
    // query table declared in HikingTrailEntity.kt
    // delete all values in the database
    @Query("DELETE FROM hiking_trail_table")
    fun deleteAll()
}