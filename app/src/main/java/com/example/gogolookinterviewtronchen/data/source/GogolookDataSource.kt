package com.example.gogolookinterviewtronchen.data.source

import androidx.lifecycle.LiveData
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.History
import com.example.gogolookinterviewtronchen.data.SearchResult
import java.util.*


interface GogolookDataSource {

    suspend fun getSearchResult(inputString: String, page: String?): AppResult<SearchResult>

    fun getAllHistory(): LiveData<List<History>>

    suspend fun insertHistory(history: History)

    suspend fun updateHistory(history: History)

    suspend fun removeHistory(date: Long)

    suspend fun clearHistory()
}