package com.example.gogolookinterviewtronchen.data.source

import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchResult


interface GogolookRepository {

    suspend fun getSearchResult(inputString: String): AppResult<SearchResult>
}