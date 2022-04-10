package live.taskr.taskr.utils

sealed class MainDestinations( val route: String ) {
    object Login: MainDestinations("tasks")
    object Register: MainDestinations("register")
    object Home: MainDestinations("home")
}