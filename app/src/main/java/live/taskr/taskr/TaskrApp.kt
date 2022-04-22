package live.taskr.taskr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import live.taskr.taskr.screens.Home
import live.taskr.taskr.screens.LoginScreen
import live.taskr.taskr.screens.RegisterScreen
import live.taskr.taskr.ui.theme.blueGrey900
import live.taskr.taskr.utils.MainDestinations

/**
 * This holds Nav graph of [Taskr]
 */
@Composable
fun TaskrApp(
    appState: TaskrAppState = rememberTaskrAppState(),

) {
//    val loginScreen = MainLoginScreen()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = blueGrey900
        )
    }
    NavHost(
        navController = appState.navController,
        startDestination = MainDestinations.Login.route
    ) {
        composable(MainDestinations.Login.route) { backStackEntry ->
            LoginScreen(
                navigateToRegister = {
                    appState.navigateToRegister()
                }
            )
        }
        composable(MainDestinations.Register.route) {
            RegisterScreen(navigateToLogin = {
                appState.navigateToHome()
            })
        }
        composable(MainDestinations.Home.route) { Home() }

    }
}