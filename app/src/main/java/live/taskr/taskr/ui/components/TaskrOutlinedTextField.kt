package live.taskr.taskr.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun TaskrOutlinedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    label: @Composable () -> Unit,
    singleLine: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small
    ) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        label = label,
        singleLine = singleLine,
        shape = shape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onBackground,
            unfocusedBorderColor = MaterialTheme.colors.onBackground.copy(0.5f),
            cursorColor = MaterialTheme.colors.onBackground,
            errorBorderColor = MaterialTheme.colors.error,
            errorCursorColor = MaterialTheme.colors.error,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.onBackground.copy(0.5f),
            textColor = MaterialTheme.colors.onBackground,
            placeholderColor = MaterialTheme.colors.onBackground
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskrOutlinedTextField() {
    TaskrTheme {
        TaskrOutlinedTextField(
            value = TextFieldValue("Email"),
            onValueChange = {},
            label = {Text("hey")}
        )
    }
}