package com.roh.dogdom.data.db.Log

import android.os.Handler
import android.os.Looper
import com.roh.dogdom.data.db.Log.Log
import com.roh.dogdom.data.db.Log.LogDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggerLocalDataSource @Inject constructor(private val logDao: LogDao) {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun addLog(msg: String) {
        executorService.execute {
            logDao.insertAll(
                Log(
                    msg,
                    System.currentTimeMillis()
                )
            )
        }
    }

    fun getAllLogs(callback: (List<Log>) -> Unit) {
        executorService.execute {
            val logs = logDao.getAll()
            mainThreadHandler.post { callback(logs) }
        }
    }

    fun removeLogs() {
        executorService.execute {
            logDao.nukeTable()
        }
    }

}