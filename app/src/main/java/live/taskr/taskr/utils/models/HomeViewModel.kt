package live.taskr.taskr.utils.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    // Sets default route to [Tasks]
    private val currentRoute = MutableStateFlow(Destinations.Tasks)
    private val routes = MutableStateFlow(Destinations.values().asList())

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {

        }
    }
}

enum class Destinations {
    Tasks, Habits
}

data class HomeViewState(
    val currentRoute: Destinations = Destinations.Tasks,
    val destinations: List<Destinations> = emptyList(),
    val refreshing: Boolean = false,
    val errMessage: String? = null
)