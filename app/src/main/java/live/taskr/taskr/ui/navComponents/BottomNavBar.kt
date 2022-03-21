package live.taskr.taskr.ui.navComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import live.taskr.taskr.R
import live.taskr.taskr.ui.components.RoundedDivider
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun TaskrBottomNavBar(
    isSelected: Boolean,
    currentRoute: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.primaryVariant),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavButton(
            title = "taskr",
            contentDescription = "",
            icon = painterResource(R.drawable.ic_tasks),
            action = { /*TODO*/ },
            isSelected = true
        )
        RoundedDivider(modifier = Modifier.align(Alignment.CenterVertically))
        NavButton(
            title = "not taskr",
            contentDescription = "",
            icon = painterResource(R.drawable.ic_tasks),
            action = { /*TODO*/ },
            isSelected = true
        )
    }
}

@Preview
@Composable
fun PreviewTaskrBottomNavBar() {
    TaskrTheme {
        TaskrBottomNavBar(true,"home")
    }
}