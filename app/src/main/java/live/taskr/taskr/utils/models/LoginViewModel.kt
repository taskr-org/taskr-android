package live.taskr.taskr.utils.models

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
    private val userName = MutableStateFlow(TextFieldValue(""))
    private val password = MutableStateFlow(TextFieldValue(""))

    private val _loginState = MutableSharedFlow<Result<String>>()
    val loginState: SharedFlow<Result<String>> = _loginState

    private val _state = MutableStateFlow(LoginViewState())
    val state: StateFlow<LoginViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                userName,
                password
            ) { userName, password ->
                LoginViewState(
                    userName = userName,
                    password = password
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }


    fun loginUser(
        UserName: TextFieldValue = userName.value,
        Password: TextFieldValue = password.value
    ) = viewModelScope.launch {
        _loginState.emit(Result.LOADING())

        if (UserName.text.isEmpty() || Password.text.isEmpty()) {
            _loginState.emit(Result.ERROR("Some Fields are empty"))
            return@launch
        }

        val newUser = User(
            username = UserName.text,
            password = Password.text
        )
        _loginState.emit(taskrRepo.loginUser(newUser))
    }
}

data class LoginViewState(
    val userName: TextFieldValue = TextFieldValue(""),
    val password: TextFieldValue = TextFieldValue(""),
//    val loginState: SharedFlow<Result<String>>,
    val refreshing: Boolean = false,
    val errMessage: String? = null
)