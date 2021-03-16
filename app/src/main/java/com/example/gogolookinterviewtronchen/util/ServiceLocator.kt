package com.example.gogolookinterviewtronchen.util
import com.example.gogolookinterviewtronchen.data.source.DefaultGogolookRepository
import com.example.gogolookinterviewtronchen.data.source.GogolookDataSource
import com.example.gogolookinterviewtronchen.data.source.local.GogolookLocalDataSource
import com.example.gogolookinterviewtronchen.data.source.remote.GogolookRemoteDataSource
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import android.content.Context
import androidx.annotation.VisibleForTesting

object ServiceLocator {

    @Volatile
    var repository: GogolookRepository? = null
        @VisibleForTesting set

    fun provideRepository(context: Context): GogolookRepository {
        synchronized(this) {
            return repository
                ?: repository
                ?: createFamilyTreeRepository(context)
        }
    }

    private fun createFamilyTreeRepository(context: Context): GogolookRepository {
        return DefaultGogolookRepository(
            GogolookRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): GogolookDataSource {
        return GogolookLocalDataSource(context)
    }
}