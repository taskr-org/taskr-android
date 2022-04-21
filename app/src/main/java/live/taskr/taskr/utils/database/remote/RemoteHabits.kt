package live.taskr.taskr.utils.database.remote

data class RemoteHabits(
    var title: String?,
    var description: String?,
    var reminder: Boolean,
    var habitId: String
)