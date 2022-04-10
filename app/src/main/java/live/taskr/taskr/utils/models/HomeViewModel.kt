package live.taskr.taskr.utils.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    // Sets default route to [Tasks]
    private val currentRoute = MutableStateFlow(Destinations.Tasks)
    private val destinations = MutableStateFlow(Destinations.values().asList())
    private val tags = MutableStateFlow(TagTypes.values().asList())
    private val defaultTag = MutableStateFlow(TagTypes.Personal)

    private val refreshing = MutableStateFlow(false)
    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                destinations,
                currentRoute,
                refreshing,
                tags,
                defaultTag
            ) {destinations, currentRoute, refreshing, tagTypes, defaultTag ->
                HomeViewState(
                    destinations = destinations,
                    currentRoute = currentRoute,
                    refreshing = refreshing,
                    errMessage = null,
                    tags = tagTypes,
                    defaultTag = defaultTag
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    fun onDestinationsSelected(destinations: Destinations) {
        currentRoute.value = destinations
    }
}

enum class Destinations {
    Tasks, Habits
}

enum class TagTypes {
    Work, Personal
}

data class HomeViewState(
    val destinations: List<Destinations> = emptyList(),
    val currentRoute: Destinations = Destinations.Tasks,
    val tags: List<TagTypes> = emptyList(),
    val defaultTag: TagTypes = TagTypes.Personal,
    val refreshing: Boolean = false,
    val errMessage: String? = null
)