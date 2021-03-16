package com.example.gogolookinterviewtronchen

import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import android.app.Application
import com.example.gogolookinterviewtronchen.util.ServiceLocator
import kotlin.properties.Delegates

class GogolookApplication : Application() {

    // Depends on the flavor,
    val repository: GogolookRepository
        get() = ServiceLocator.provideRepository(this)

    companion object {
        var INSTANCE: GogolookApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    fun isLiveDataDesign() = true
}