package live.taskr.taskr

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import live.taskr.taskr.utils.MainDestinations
import live.taskr.taskr.utils.models.HomeViewModel
import live.taskr.taskr.utils.models.LoginViewModel

@Composable
fun rememberTaskrAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
) = remember(navController, context){
    TaskrAppState(navController, context)
}

class TaskrAppState(
    val navController: NavHostController,
    private val context: Context,

) {
    var isOnline by mutableStateOf(checkIfOnline())
        private set

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToRegister() {
        navController.navigate(MainDestinations.Register.route)
    }

    fun navigateToLogin() {
        navController.navigate(MainDestinations.Login.route)
    }

    fun navigateToHome() {
        navController.navigate(MainDestinations.Home.route)
    }

    private fun checkIfOnline(): Boolean {
        val connectivityManager = getSystemService(context, ConnectivityManager::class.java)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = connectivityManager?.getNetworkCapabilities(
                connectivityManager.activeNetwork) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting == true
        }
    }
}
/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED