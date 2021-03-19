package com.example.gogolookinterviewtronchen.data.source

import androidx.lifecycle.LiveData
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.History
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.local.GogolookLocalDataSource
import com.example.gogolookinterviewtronchen.data.source.remote.GogolookRemoteDataSource
import java.util.*

class DefaultGogolookRepository(private val remoteDataSource: GogolookDataSource,
                                private val localDataSource: GogolookDataSource
) : GogolookRepository {

    override suspend fun getSearchResult(inputString: String, page: String): AppResult<SearchResult>{
        return remoteDataSource.getSearchResult(inputString , page)
    }

    override fun getAllHistory(): LiveData<List<History>> {
        return localDataSource.getAllHistory()
    }

    override suspend fun insertHistory(history: History) {
        return localDataSource.insertHistory(history)
    }

    override suspend fun updateHistory(history: History) {
       return localDataSource.updateHistory(history)
    }

    override suspend fun removeHistory(date: Long) {
        return localDataSource.removeHistory(date)
    }

    override suspend fun clearHistory() {
        return localDataSource.clearHistory()
    }
}