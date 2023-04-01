package com.kate.interviewtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kate.interviewtask.model.SourceModel


@Database(entities = [SourceModel::class], version = 1, exportSchema = false)
abstract class SourceDatabase : RoomDatabase() {
    abstract val sourceDao: SourceDao

    companion object {
        @Volatile
        private var INSTANCE: SourceDatabase? = null
        fun getInstance(context: Context): SourceDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SourceDatabase::class.java,
                        "source_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}