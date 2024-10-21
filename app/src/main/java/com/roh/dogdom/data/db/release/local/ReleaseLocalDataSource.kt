package com.roh.dogdom.data.db.release.local

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReleaseLocalDataSource @Inject constructor(private val releaseDao: ReleaseDao) {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun add(
        release: ReleaseEntity
    ) {
        executorService.execute {
            releaseDao.insertAll(
                release
            )
        }
    }

    fun getAll(callback: (List<ReleaseEntity>) -> Unit) {
        executorService.execute {
            val release = releaseDao.getAll()
            mainThreadHandler.post { callback(release) }
        }
    }

    fun remove() {
        executorService.execute {
            releaseDao.nukeTable()
        }
    }

}