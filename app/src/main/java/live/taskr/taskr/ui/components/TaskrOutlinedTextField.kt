package live.taskr.taskr.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue

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
//    var value by remember{ mutableStateOf("") }
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
            unfocusedBorderColor = ,
            cursorColor = ,
            errorBorderColor = ,
            errorCursorColor = ,
            trailingIconColor = ,
        )
    )
}