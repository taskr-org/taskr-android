package live.taskr.taskr.utils.database.remote

data class RemoteTasks(
    var title: String?,
    var description: String?,
    var type: String?,
    var reminder: Boolean,
    var date: Long,
    var time: String,
    var priority: String,
    var link: String,
    var taskId: String
)
