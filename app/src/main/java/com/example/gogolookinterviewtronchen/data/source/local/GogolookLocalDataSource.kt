package com.example.gogolookinterviewtronchen.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.History
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.GogolookDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class GogolookLocalDataSource(val context: Context) : GogolookDataSource {

    override suspend fun getSearchResult(inputString: String, page: String?): AppResult<SearchResult> {
        TODO("Not yet implemented")
    }

    override fun getAllHistory(): LiveData<List<History>> {
        return GogolookDatabase.getInstance(context).gogolookDatabaseDao.getAllHistory()
    }

    override suspend fun insertHistory(history: History) {
        withContext(Dispatchers.IO) {
            GogolookDatabase.getInstance(context).gogolookDatabaseDao.insert(history)
        }
    }

    override suspend fun updateHistory(history: History) {
        withContext(Dispatchers.IO) {
            GogolookDatabase.getInstance(context).gogolookDatabaseDao.update(history)
        }
    }

    override suspend fun removeHistory(date: Long) {
        withContext(Dispatchers.IO) {
            GogolookDatabase.getInstance(context).gogolookDatabaseDao.delete(date)
        }
    }

    override suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            GogolookDatabase.getInstance(context).gogolookDatabaseDao.clear()
        }
    }
}