package live.taskr.taskr.utils.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class LocalTasks(
    var title: String? = null,
    var description: String? = null,
    var type: String? = null,
    var reminder: Boolean = false,
    var date: Long = System.currentTimeMillis(),
    var time: String? = null,
    var priority: String? = null,
    var link: String? = null,
    var isOnline: Boolean = false,
    var locallyDeleted: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var taskId: Long
): Serializable
