package live.taskr.taskr.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.taskr.taskr.R
import live.taskr.taskr.ui.components.TaskrOutlinedTextField
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun RegisterScreen() {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
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
            userName = userName,
            onUserNameChange = { userName = it},
            password = password,
            onPasswordChange = { password = it }
        )
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
    onPasswordChange: (TextFieldValue) -> Unit
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
            onClick = { /*TODO*/ },
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
                .wrapContentWidth(Alignment.End)
        )
        Text(
            text = "- or Sign up with -",
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .wrapContentWidth(CenterHorizontally)
        )
        Oauth(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp)
        )
        OutlinedButton(
            onClick = { /*TODO*/ },
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
            onPasswordChange = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    TaskrTheme {
        RegisterScreen()
    }
}