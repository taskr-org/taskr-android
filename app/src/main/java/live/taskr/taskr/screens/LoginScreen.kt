package live.taskr.taskr.screens

import android.util.Log
import android.util.Log.VERBOSE
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import live.taskr.taskr.R
import live.taskr.taskr.TaskrAppState
import live.taskr.taskr.rememberTaskrAppState
import live.taskr.taskr.ui.components.TaskrOutlinedTextField
import live.taskr.taskr.ui.theme.TaskrTheme
import live.taskr.taskr.utils.models.LoginViewModel
import live.taskr.taskr.utils.models.UserViewModel
import live.taskr.taskr.utils.networking.Result

@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
) {
    val viewModel = hiltViewModel<LoginViewModel>()


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    IsLoggedIn()
    Surface {
        Column {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
            Text(
                text = stringResource(id = R.string.quote),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LoginFields(
                username = username,
                onUserNameChange = { username = it },
                password = password,
                onPasswordChange = { password = it },
                navigateToRegister = navigateToRegister,
                loginUser = { viewModel.loginUser(username, password) }
            )
        }

    }
}

@Composable
fun IsLoggedIn(
    appState: TaskrAppState = rememberTaskrAppState()
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(viewModel.loginState) {
        coroutineScope.launch {
            viewModel.loginState.collect { result ->
                when (result) {
                    is Result.SUCCESS -> {
                        Log.v("Login:","success")
                        appState.navigateToHome()
                    }
                    is Result.ERROR -> {
                        // TODO
                        Log.e("Login:","error")
                    }
                    is Result.LOADING -> {
                        // TODO
                        Log.e("Login:","loading")
                    }
                }
            }
        }
    }
}

@Composable
fun LoginFields(
    username: String,
    onUserNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    navigateToRegister: () -> Unit,
    loginUser: () -> Unit
) {
    Column(
        Modifier
            .fillMaxHeight()
            .wrapContentSize(align = Alignment.BottomCenter)
            .padding(20.dp)
    ) {
        Column {
            TaskrOutlinedTextField(
                value = username,
                onValueChange = onUserNameChange,
                label = { Text("Username") },
            )
            TaskrOutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
            )
            Button(
                onClick = loginUser,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    contentColor = MaterialTheme.colors.background
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "Sign in",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                        .wrapContentWidth(CenterHorizontally)
                )
            }
            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(End)
            )
            Text(
                text = "- or Sign in with -",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
                    .wrapContentWidth(CenterHorizontally)
            )
            Oauth(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            )
            OutlinedButton(
                onClick = navigateToRegister,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colors.onBackground
                ),
                border = BorderStroke(1.dp, color = MaterialTheme.colors.onBackground)
            ) {
                Text(
                    text = "Don't have an account?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                        .wrapContentWidth(CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun Oauth(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(end = 10.dp)
                .size(50.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onBackground
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Sign in with google"
            )
        }

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onBackground
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = "Sign in with google"
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewLoginScreen() {
//    TaskrTheme {
//        MainLoginScreen().LoginScreen(
//            navigateToRegister = {}
//        )
//    }
//}