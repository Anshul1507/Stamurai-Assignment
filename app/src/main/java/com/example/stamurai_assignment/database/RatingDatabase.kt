package com.example.stamurai_assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Rating::class],version = 1,exportSchema = false)
abstract class RatingDatabase : RoomDatabase() {
    abstract val sleepDatabaseDao: RatingDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: RatingDatabase? = null

        fun getInstance(context : Context) : RatingDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            RatingDatabase::class.java,
                            "rating_history_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
