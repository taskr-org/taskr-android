package live.taskr.taskr.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.taskr.taskr.R
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Surface() {
        Column() {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LoginFields(
                username = username,
                onUserNameChange = {username = it},
                password = password,
                onPasswordChange = {password = it}
            )
        }

    }
}

@Composable
fun LoginFields(
    username: String,
    onUserNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    Column(Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.BottomCenter)) {
        Column() {
            OutlinedTextField(
                value = username,
                onValueChange = onUserNameChange,
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp)
            )
            Button(onClick = { /*TODO*/ }) {
                Text(
                    text = "Sign in",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = "policy crap here",
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}

@Preview(showBackground = false)
@Composable
fun PreviewLoginFields() {
    TaskrTheme {
        LoginFields(
            username = "Username",
            onUserNameChange = {},
            password = "Password",
            onPasswordChange = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    TaskrTheme {
        LoginScreen()
    }
}