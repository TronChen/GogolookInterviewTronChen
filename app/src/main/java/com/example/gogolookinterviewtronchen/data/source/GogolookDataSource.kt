package com.example.gogolookinterviewtronchen.data.source

import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchResult


interface GogolookDataSource {

    suspend fun getSearchResult(inputString: String, page: String?): AppResult<SearchResult>
}