package live.taskr.taskr.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
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
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        label = label,
        singleLine = singleLine,
        shape = shape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.secondaryVariant,
            unfocusedBorderColor = MaterialTheme.colors.secondaryVariant.copy(0.5f),
            cursorColor = MaterialTheme.colors.secondaryVariant,
            errorBorderColor = MaterialTheme.colors.error,
            errorCursorColor = MaterialTheme.colors.error,
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