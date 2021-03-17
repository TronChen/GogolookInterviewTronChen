package com.example.gogolookinterviewtronchen.data.source.local

import android.content.Context
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.GogolookDataSource

class GogolookLocalDataSource(val context: Context) : GogolookDataSource {

    override suspend fun getSearchResult(inputString: String): AppResult<SearchResult> {
        TODO("Not yet implemented")
    }

}