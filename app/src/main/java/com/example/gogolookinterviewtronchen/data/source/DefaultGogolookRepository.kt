package com.example.gogolookinterviewtronchen.data.source

import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.remote.GogolookRemoteDataSource

class DefaultGogolookRepository(private val remoteDataSource: GogolookDataSource,
                                private val localDataSource: GogolookDataSource
) : GogolookRepository {

    override suspend fun getSearchResult(inputString: String): AppResult<SearchResult>{
        return GogolookRemoteDataSource.getSearchResult(inputString)
    }
}