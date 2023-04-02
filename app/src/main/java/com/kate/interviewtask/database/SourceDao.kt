package com.kate.interviewtask.database

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM source WHERE url = :url")
    fun get(url: String): LiveData<SourceModel>

    @Query("SELECT * FROM source")
    fun getAll(): LiveData<List<SourceModel>>

    @Query("SELECT * FROM source WHERE fav = 1")
    fun getAllFav(): LiveData<List<SourceModel>>
}