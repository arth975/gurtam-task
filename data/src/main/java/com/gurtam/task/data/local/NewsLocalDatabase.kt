package com.gurtam.task.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gurtam.task.data.local.NewsLocalDatabase.Companion.DB_VERSION
import com.gurtam.task.data.local.dao.ArticleDao
import com.gurtam.task.data.local.dao.NewsSourceDao
import com.gurtam.task.data.local.entites.ArticleEntity
import com.gurtam.task.data.local.entites.NewsSourceEntity
import com.gurtam.task.data.local.typeadapters.LocalDateTimeTypeConverter

@Database(entities = [NewsSourceEntity::class, ArticleEntity::class], version = DB_VERSION)
@TypeConverters(LocalDateTimeTypeConverter::class)
abstract class NewsLocalDatabase : RoomDatabase() {

    companion object {
        const val DB_VERSION = 5
        private const val DB_NAME = "com.gurtam.task.NEWS_DB"

        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            NewsLocalDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    abstract fun newsSourceDao(): NewsSourceDao
    abstract fun articleDao(): ArticleDao
}