package com.roh.dogdom.data.db.user

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val userDao: UserDao) {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun addUser(info: UserEntity) {
        executorService.execute {
            userDao.insertAll(info)
        }
    }

    fun getAllUsers(callback: (List<UserEntity>) -> Unit) {
        executorService.execute {
            val usersInfo = userDao.getAll()
            mainThreadHandler.post { callback(usersInfo)}
        }
    }

    fun removeUsers() {
        executorService.execute {
            userDao.nukeTable()
        }
    }
}