package live.taskr.taskr.utils.models

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import live.taskr.taskr.utils.database.User
import live.taskr.taskr.utils.networking.Result
import live.taskr.taskr.utils.repository.TaskrRepo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val taskrRepo: TaskrRepo
) : ViewModel() {
    private val _loginState = MutableSharedFlow<Result<String>>()
    val loginState: SharedFlow<Result<String>> = _loginState

    private val _state = MutableSharedFlow<Result<User>>()
    val state: SharedFlow<Result<User>> = _state

//    init {
//        viewModelScope.launch {
//            combine(
//                userName,
//                password
//            ) { userName, password ->
//                LoginViewState(
//                    userName = userName,
//                    password = password
//                )
//            }.catch { throwable ->
//                throw throwable
//            }.collect {
//                _state.value = it
//            }
//        }
//    }


    fun loginUser(
        UserName: String,
        Password: String
    ){
        viewModelScope.launch {
            _loginState.emit(Result.LOADING())
            Log.e("Login:", "who summoned me?")
            if (UserName.isEmpty() || Password.isEmpty()) {
                _loginState.emit(Result.ERROR("Some Fields are empty"))
                return@launch
            }

            val newUser = User(
                username = UserName,
                password = Password
            )
            _loginState.emit(taskrRepo.loginUser(newUser))
        }
    }
}

//data class LoginViewState(
//    val userName: TextFieldValue = TextFieldValue(""),
//    val password: TextFieldValue = TextFieldValue(""),
////    val loginState: SharedFlow<Result<String>>,
//    val refreshing: Boolean = false,
//    val errMessage: String? = null
//)