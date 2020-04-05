package com.example.stamurai_assignment.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RatingDatabaseDao{

    @Insert
    fun insert(rating: Rating)

    @Update
    fun update(rating: Rating)

    @Query("SELECT * from rating_table WHERE ratingId = :key")
    fun get(key:Long): Rating?

    @Query("DELETE FROM rating_table")
    fun clear()

    @Query("SELECT * FROM rating_table ORDER BY ratingId DESC")
    fun getAllRatings(): LiveData<List<Rating>>
}
