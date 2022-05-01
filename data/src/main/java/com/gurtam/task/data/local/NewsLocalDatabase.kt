package com.gurtam.task.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gurtam.task.data.local.NewsLocalDatabase.Companion.DB_VERSION
import com.gurtam.task.data.local.dao.NewsSourceDao
import com.gurtam.task.data.local.entites.NewsSourceEntity

@Database(entities=[NewsSourceEntity::class], version = DB_VERSION)
abstract class NewsLocalDatabase : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "com.gurtam.task.NEWS_DB"

        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            NewsLocalDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    abstract fun newsSourceDao(): NewsSourceDao
}