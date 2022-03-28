package live.taskr.taskr.ui.navComponents

import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import live.taskr.taskr.R
import live.taskr.taskr.utils.models.Destinations

@Composable
fun TaskrTabRow(
    currentRoute: Destinations,
    onRouteSelected: (Destinations) -> Unit,
    modifier: Modifier = Modifier,
    destinations: List<Destinations>,
) {
    val selectedIndex = destinations.indexOfFirst { it == currentRoute }
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier
    ) {
        destinations.forEachIndexed { index, destinations ->
            TabButton(
                title = when (destinations) {
                    Destinations.Habits -> stringResource(R.string.Habits)
                    Destinations.Tasks -> stringResource(R.string.Tasks)
                },
                contentDescription = stringResource(R.string.TabBtnContentDescription),
                icon = painterResource(when (destinations) {
                    Destinations.Habits -> R.drawable.ic_tasks
                    Destinations.Tasks -> R.drawable.ic_tasks
                }),
                onClick = { onRouteSelected(destinations) },
                isSelected = index == selectedIndex
            )
        }
    }
}