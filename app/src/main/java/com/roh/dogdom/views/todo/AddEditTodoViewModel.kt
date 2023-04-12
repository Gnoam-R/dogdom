package com.roh.dogdom.views.todo


import androidx.lifecycle.*
import com.roh.dogdom.data.todo.Todo
import com.roh.dogdom.data.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _todo = MutableLiveData<Todo>(null)
    val todo: LiveData<Todo> get()= _todo

    private var _title = MutableLiveData<String>("")
    val title: LiveData<String?> get() = _title

    private var _description = MutableLiveData<String>("")
    val description: LiveData<String?> get() = _description

//    private val _uiEvent = Channel<UiEvent>()
//    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description
                    this@AddEditTodoViewModel.todo = todo
                }

            }
        }
    }
    fun onEvent() {
        viewModelScope.launch {
            if(title.isBlank()){

            }
            repository.insertTodo(
                Todo(
                    title = "title",
                    description =  "description",
                    isDone = todo?.isDone ?: false,
                    id = todo?.id
                )
            )
        }
    }

//    fun onEvent(event: AddEditTodoEvent) {
//        when(event){
//            is AddEditTodoEvent.OntitleChange ->{
//                title= event.title
//            }
//            is AddEditTodoEvent.OnDescriptionChange ->{
//                description = event.description
//            }
//            is AddEditTodoEvent.OnSaveTodoClick ->{
//                viewModelScope.launch {
//                    if(title.isBlank()){
//                        sendUiEvent(UiEvent.ShowSnackbar(
//                            message = "The title can't be empty"
//                        ))
//                        return@launch
//                    }
//                    repository.insertTodo(
//                        Todo(
//                            title =title,
//                            description =  description,
//                            isDone = todo?.isDone ?: false,
//                            id = todo?.id
//                        )
//                    )
//                    sendUiEvent(UiEvent.PopBackStack)
//                }
//            }
//        }
//    }

//    private fun sendUiEvent(event: UiEvent) {
//        viewModelScope.launch {
//            _uiEvent.send(event)
//        }
//    }
}