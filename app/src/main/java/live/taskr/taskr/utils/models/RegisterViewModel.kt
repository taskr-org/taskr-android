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
import java.util.regex.Pattern
import javax.inject.Inject

class RegisterViewModel (
    private val taskrRepo: TaskrRepo
) : ViewModel() {

    private val fullName = MutableStateFlow(TextFieldValue(""))
    private val userName = MutableStateFlow(TextFieldValue(""))
    private val email = MutableStateFlow(TextFieldValue(""))
    private val password = MutableStateFlow(TextFieldValue(""))

    private val _registerState = MutableSharedFlow<Result<String>>()
    val registerState: SharedFlow<Result<String>> = _registerState

    fun createUser(
        FullName: TextFieldValue = fullName.value,
        UserName: TextFieldValue = userName.value,
        Email: TextFieldValue = email.value,
        Password: TextFieldValue = password.value
    ) = viewModelScope.launch {
        _registerState.emit(Result.LOADING())

        if (FullName.text.isEmpty() || UserName.text.isEmpty() || Email.text.isEmpty() || Password.text.isEmpty()) {
            _registerState.emit(Result.ERROR("Some Fields are empty"))
            return@launch
        }

        if(!isEmailValid(Email)){
            _registerState.emit(Result.ERROR("Email is not Valid!"))
            return@launch
        }

        val newUser = User(
            fullName = FullName.text,
            username = UserName.text,
            email = Email.text,
            password = Password.text
        )
        _registerState.emit(taskrRepo.registerUser(newUser))
    }

    private val _state = MutableStateFlow(RegisterViewState())
    val state: StateFlow<RegisterViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                fullName,
                userName,
                email,
                password
            ) { fullName, userName, email, password ->
                RegisterViewState(
                    fullName = fullName,
                    userName = userName,
                    email = email,
                    password = password
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    private fun isEmailValid(email: TextFieldValue):Boolean {
        val regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        val pattern = Pattern.compile(regex)
        return (email.text.isNotEmpty() && pattern.matcher(email.text).matches())
    }
}

data class RegisterViewState(
    val fullName: TextFieldValue = TextFieldValue(""),
    val userName: TextFieldValue = TextFieldValue(""),
    val email: TextFieldValue = TextFieldValue(""),
    val password: TextFieldValue = TextFieldValue(""),
    val refreshing: Boolean = false,
    val errMessage: String? = null
)