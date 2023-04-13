package com.roh.dogdom.data.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    /** Todo :  OnConflictStrategy.REPLACE
    *이미 동일한 Primary key를 가진 데이터가 있는 경우
    * 기존 데이터를 대체하여 충동을 처리하는 방식
     * GNORE, ABORT, FAIL, ROLLBACK, REPLACE 등 다양한 전력을 제공
     * */

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id:Int) : Todo?

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>
}