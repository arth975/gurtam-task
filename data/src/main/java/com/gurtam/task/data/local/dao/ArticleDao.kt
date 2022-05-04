package com.gurtam.task.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.gurtam.task.data.local.entites.ArticleEntity

@Dao
interface ArticleDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles WHERE newsSourceId = :newsSourceId")
    suspend fun clearAllBySourceId(newsSourceId: String)

    @Query("SELECT * FROM articles WHERE newsSourceId = :newsSourceId")
    fun articlesBySourceIdPageSource(newsSourceId: String): PagingSource<Int, ArticleEntity>

    @Transaction
    suspend fun refresh(articles: List<ArticleEntity>, newsSourceId: String) {
        clearAllBySourceId(newsSourceId)
        insertAll(articles)
    }
}