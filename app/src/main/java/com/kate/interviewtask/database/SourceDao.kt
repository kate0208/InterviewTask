package com.kate.interviewtask.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.kate.interviewtask.model.SourceModel

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(source: SourceModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(sources: List<SourceModel>)

    @Update
    suspend fun update(source: SourceModel)

    @Delete
    suspend fun delete(source: SourceModel)

    @Query("SELECT * FROM source WHERE date = :date")
    fun get(date: String): LiveData<SourceModel>

    @Query("SELECT * FROM source")
    fun getAll(): PagingSource<Int, SourceModel>

    @Query("SELECT COUNT(*) FROM source")
    fun getAllCount(): LiveData<Int>

    @Query("SELECT * FROM source WHERE fav = 1")
    fun getAllFav(): PagingSource<Int, SourceModel>

    @Query("SELECT COUNT(*) FROM source WHERE fav = 1")
    fun getAllFavCount(): LiveData<Int>
}