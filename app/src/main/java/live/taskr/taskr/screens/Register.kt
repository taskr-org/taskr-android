package live.taskr.taskr.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.taskr.taskr.R
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun RegisterScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column() {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(CenterHorizontally)
        )
        LoginFields(
            username = username,
            onUserNameChange = {username = it},
            password = password,
            onPasswordChange = {password = it})
    }
}

@Composable
fun RegisterFields(
    fullName: String,
    onFullNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    username: String,
    onUserNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        OutlinedTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterFields() {
    TaskrTheme {
        RegisterFields(
            fullName = "Full Name",
            onFullNameChange = {},
            email = "Email Address",
            onEmailChange = {},
            username = "Username",
            onUserNameChange = {},
            password = "Password",
            onPasswordChange = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    TaskrTheme {
        LoginScreen()
    }
}