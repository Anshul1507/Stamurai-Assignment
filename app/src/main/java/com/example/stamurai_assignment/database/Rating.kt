package com.example.stamurai_assignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rating_table")
data class Rating(
      @PrimaryKey(autoGenerate = true)
      var ratingId: Long = 0L,

      @ColumnInfo(name = "start_time_milli")
      val startTimeMilli: Long = System.currentTimeMillis(),

      @ColumnInfo(name = "total_rating")
      var totalRating: Int = -1
)
