package com.roh.dogdom.data.db

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseLocalDataSource @Inject constructor(private val baseDao: BaseDao) {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun addLog(msg: String) {
        executorService.execute {
            baseDao.insertAll(
                BaseEntity(
                    msg,
                    System.currentTimeMillis()
                )
            )
        }
    }

    fun getAllLogs(callback: (List<BaseEntity>) -> Unit) {
        executorService.execute {
            val base = baseDao.getAll()
            mainThreadHandler.post { callback(base) }
        }
    }

    fun removeLogs() {
        executorService.execute {
            baseDao.nukeTable()
        }
    }

}