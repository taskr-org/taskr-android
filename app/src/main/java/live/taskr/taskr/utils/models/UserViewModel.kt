package live.taskr.taskr.utils.models

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import live.taskr.taskr.utils.database.User
import live.taskr.taskr.utils.networking.Result
import live.taskr.taskr.utils.repository.TaskrRepo
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val taskrRepo: TaskrRepo
): ViewModel() {
    private val _currentUserState = MutableSharedFlow<Result<User>>()
    val currentUserState:SharedFlow<Result<User>> = _currentUserState

    private fun getCurrentUser()  = viewModelScope.launch{
        _currentUserState.emit(Result.LOADING())
        _currentUserState.emit(taskrRepo.getUser())
    }

    fun logout() = viewModelScope.launch {
        val result = taskrRepo.logout()
        if(result is Result.SUCCESS){
            getCurrentUser()
        }
    }
}