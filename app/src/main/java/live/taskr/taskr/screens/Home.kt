package live.taskr.taskr.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import live.taskr.taskr.ui.navComponents.TaskrTopAppBar
import live.taskr.taskr.utils.models.Destinations
import live.taskr.taskr.utils.models.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()
    HomeContent(
        currentRoute = viewState.currentRoute,
        onRouteSelected = viewModel::onDestinationsSelected,
        destinations = viewState.destinations
    )
}

@Composable
fun HomeContent(
    currentRoute: Destinations,
    onRouteSelected: (Destinations) -> Unit,
    destinations: List<Destinations>
) {
    Scaffold(
        topBar = {
            TaskrTopAppBar(
                userName = "xyz",
                quote = "xyz",
                currentRoute = currentRoute,
                onRouteSelected = onRouteSelected,
                destinations = destinations
            )
        }
    ) {
    }
}