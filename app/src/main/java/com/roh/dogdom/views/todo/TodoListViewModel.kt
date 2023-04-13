package com.roh.dogdom.views.todo

import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roh.dogdom.data.todo.Todo
import com.roh.dogdom.data.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    val todos = repository.getTodos()

    init {
//        val todoId = savedStateHandle.get<Int>("todoId")!!
//        if(todoId != -1) {
//            Log.e("TodoListViewModel","check 1")
//            viewModelScope.launch {
//                repository.getTodoById(todoId)?.let { todo ->
//                    title = todo.title
//                    description = todo.description ?: ""
//                    this@AddEditTodoViewModel.todo = todo
//                }
//            }
//        }
//        Log.e("TodoListViewModel","check 2")

    }

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        repository.insertTodo(todo)
    }
    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }
    fun getTodoById(id:Int) = viewModelScope.launch {
        repository.getTodoById(id)
    }
    fun getTodos() = viewModelScope.launch {
        repository.getTodos()
    }

//    public fun checkBox(view: View) {
//        Log.e("TodoListViewModel","check 4")
//    }


    private var deletedTodo : Todo? = null

}