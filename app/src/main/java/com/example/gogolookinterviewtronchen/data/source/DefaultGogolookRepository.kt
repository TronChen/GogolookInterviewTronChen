package com.example.gogolookinterviewtronchen.data.source

class DefaultGogolookRepository(private val remoteDataSource: GogolookDataSource,
                                private val localDataSource: GogolookDataSource
) : GogolookRepository {

}