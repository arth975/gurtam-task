package com.gurtam.task.data.local.dao

import androidx.room.*
import com.gurtam.task.data.local.entites.NewsSourceEntity

@Dao
interface NewsSourceDao {

    @Query("SELECT * FROM newsSources")
    suspend fun getAllNewsSources(): List<NewsSourceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newsSource: List<NewsSourceEntity>)

    @Query("DELETE FROM newsSources")
    suspend fun deleteAll()

    @Transaction
    suspend fun refreshNewsSources(newsSources: List<NewsSourceEntity>) {
        deleteAll()
        insert(newsSources)
    }
}