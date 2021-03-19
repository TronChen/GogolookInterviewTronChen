package com.example.gogolookinterviewtronchen.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gogolookinterviewtronchen.data.History
import java.util.*

@Dao
interface GogolookDatabaseDao {

    @Insert
    fun insert(history: History)

    @Update
    fun update(history: History)

    @Query("DELETE from search_history WHERE date = :date ")
    fun delete(date: Date)

    @Query("DELETE FROM search_history")
    fun clear()

    @Query("SELECT * FROM search_history ORDER BY date ASC")
    fun getAllHistory():
            LiveData<List<History>>


}