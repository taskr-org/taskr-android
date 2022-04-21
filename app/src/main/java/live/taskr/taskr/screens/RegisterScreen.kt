package live.taskr.taskr.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import live.taskr.taskr.R
import live.taskr.taskr.TaskrAppState
import live.taskr.taskr.rememberTaskrAppState
import live.taskr.taskr.ui.components.TaskrOutlinedTextField
import live.taskr.taskr.ui.theme.TaskrTheme
import live.taskr.taskr.utils.models.LoginViewModel
import live.taskr.taskr.utils.models.RegisterViewModel
import live.taskr.taskr.utils.networking.Result

@Composable
fun RegisterScreen(
    navigateToLogin: () -> Unit,
) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val viewState by viewModel.state.collectAsState()

    var fullName by remember { mutableStateOf(viewState.fullName) }
    var username by remember { mutableStateOf(viewState.userName) }
    var password by remember { mutableStateOf(viewState.password) }
    var email by remember { mutableStateOf(viewState.email) }

    IsRegisterd()
    Column() {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.quote),
            modifier = Modifier.align(CenterHorizontally)
        )
        RegisterFields(
            fullName = fullName,
            onFullNameChange = { fullName = it },
            email = email,
            onEmailChange = { email = it },
            userName = username,
            onUserNameChange = { username = it },
            password = password,
            onPasswordChange = { password = it },
            navigateToLogin = navigateToLogin,
            registerUser = viewModel::createUser
        )
    }
}

@Composable
fun IsRegisterd(
    appState: TaskrAppState = rememberTaskrAppState()
) {

    val viewModel = hiltViewModel<RegisterViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val viewState by viewModel.state.collectAsState()
    LaunchedEffect(viewState) {
        coroutineScope.launch {
            viewModel.registerState.collect { result ->
                when (result) {
                    is Result.SUCCESS -> {
                        Log.e("Register:","success")
                        appState.navigateToHome()
                    }
                    is Result.ERROR -> {

                        Log.e("Register:","ERROR")
                        // TODO
                    }
                    is Result.LOADING -> {
                        // TODO

                        Log.e("Register:","loading")
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterFields(
    fullName: TextFieldValue,
    onFullNameChange: (TextFieldValue) -> Unit,
    email: TextFieldValue,
    onEmailChange: (TextFieldValue) -> Unit,
    userName: TextFieldValue,
    onUserNameChange: (TextFieldValue) -> Unit,
    password: TextFieldValue,
    onPasswordChange: (TextFieldValue) -> Unit,
    navigateToLogin: () -> Unit,
    registerUser: () -> Unit
) {
    Column(
        Modifier
            .fillMaxHeight()
            .wrapContentSize(align = Alignment.BottomCenter)
            .padding(20.dp)
    ) {
        TaskrOutlinedTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            label = { Text("Full Name") }
        )
        TaskrOutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email Address") }
        )
        TaskrOutlinedTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text("Username") }
        )
        TaskrOutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") }
        )
        Button(
            onClick = registerUser,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = MaterialTheme.colors.background
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = "create account",
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
                .wrapContentWidth(Alignment.End)
        )
        Text(
            text = "- or Sign up with -",
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
            onClick = { navigateToLogin },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onBackground
            ),
            border = BorderStroke(1.dp, color = MaterialTheme.colors.onBackground)
        ) {
            Text(
                text = "Already Have an Account?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .wrapContentWidth(CenterHorizontally)
            )
        }
    }
}

//@Composable
//fun Oauth(modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        OutlinedButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .padding(end = 10.dp)
//                .size(50.dp),
//            shape = CircleShape,
//            border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
//            contentPadding = PaddingValues(0.dp),
//            colors = ButtonDefaults.outlinedButtonColors(
//                contentColor = MaterialTheme.colors.onBackground
//            )
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_google),
//                contentDescription = "Sign in with google"
//            )
//        }
//
//        OutlinedButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier.size(50.dp),
//            shape = CircleShape,
//            border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
//            contentPadding = PaddingValues(0.dp),
//            colors = ButtonDefaults.outlinedButtonColors(
//                contentColor = MaterialTheme.colors.onBackground
//            )
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_twitter),
//                contentDescription = "Sign in with google"
//            )
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterFields() {
    TaskrTheme {
        RegisterFields(
            fullName = TextFieldValue("FullName"),
            onFullNameChange = {},
            email = TextFieldValue("Email"),
            onEmailChange = {},
            userName = TextFieldValue("Username"),
            onUserNameChange = {},
            password = TextFieldValue("Password"),
            onPasswordChange = {},
            navigateToLogin = {},
            registerUser = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    TaskrTheme {
        RegisterScreen(
            navigateToLogin = {}
        )
    }
}