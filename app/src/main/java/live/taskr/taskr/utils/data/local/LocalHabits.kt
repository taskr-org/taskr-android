package live.taskr.taskr.utils.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LocalHabits(
    var title: String,
    var description: String? = null,
    var reminder: Boolean = false,
    var isOnline: Boolean = false,
    var locallyDeleted: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var habitId: String = UUID.randomUUID().toString()
)
