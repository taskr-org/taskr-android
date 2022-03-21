package live.taskr.taskr.ui.navComponents

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import live.taskr.taskr.R

sealed class MainDestinations(
    val route: String? = null,
    @StringRes val title: Int? = null,
    val icon: @Composable (() -> Unit)? = null
) {
    object tasks: MainDestinations("Tasks", R.string.Tasks)
}

