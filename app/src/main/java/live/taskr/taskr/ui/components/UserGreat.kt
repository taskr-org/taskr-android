package live.taskr.taskr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import live.taskr.taskr.R
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun UserGreat(
    username: String,
    quote: String,
    userIcon: @Composable () -> Unit
) {
    Row() {

    }
}

@Preview
@Composable
fun PreviewUserGreat() {
    TaskrTheme {
        UserGreat(
            username = "Jayesh Seth",
            quote = "hemlo there!",
            userIcon = { Image(painter = painterResource(R.drawable.ic_tasks),"") }
        )
    }
}