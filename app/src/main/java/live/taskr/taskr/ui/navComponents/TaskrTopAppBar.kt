package live.taskr.taskr.ui.navComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import live.taskr.taskr.R
import live.taskr.taskr.utils.models.Destinations

@Composable
fun TaskrTopAppBar(
    userName: String,
    quote: String,
    modifier: Modifier = Modifier,
    currentRoute: Destinations,
    onRouteSelected: (Destinations) -> Unit,
    destinations: List<Destinations>,
) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                Text(
                    text = "Hi $userName",
                )
                Text(
                    text = quote,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_profile_logo),
                contentDescription = ""
            )
        }

        TaskrTabRow(
            currentRoute = currentRoute,
            onRouteSelected = onRouteSelected,
            destinations = destinations)
    }
}

@Preview
@Composable
fun PreviewUserInfo() {
//    TaskTopAppBar(
//        userName = "Jayesh Seth",
//        quote = "gae",
//        currentRoute = Destinations.Tasks,
//        onRouteSelected = {},
//        destinations = listOf())
}

