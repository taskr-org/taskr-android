package live.taskr.taskr.utils.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class LocalHabits(
    var title: String? = null,
    var description: String? = null,
    var reminder: Boolean = false,
    var isOnline: Boolean = false,
    var locallyDeleted: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var habitId: Long
): Serializable
