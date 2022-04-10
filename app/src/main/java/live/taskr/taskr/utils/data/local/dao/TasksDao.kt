package live.taskr.taskr.utils.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import live.taskr.taskr.utils.data.local.LocalTasks

interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: LocalTasks)

    @Query("SELECT * FROM localtasks WHERE locallyDeleted = 0 ORDER BY date DESC")
    fun getTasksOrderByDate(): Flow<List<LocalTasks>>

    @Query("DELETE FROM localtasks WHERE taskId =:taskId")
    suspend fun deleteTask(taskId: String)

    @Query("UPDATE localtasks SET locallyDeleted = 1 WHERE taskId = :taskId")
    suspend fun deleteTaskLocally(taskId: String)

    @Query("SELECT * FROM localtasks WHERE isOnline = 0")
    suspend fun getLocalTasks(): List<LocalTasks>

    @Query("SELECT * FROM localtasks WHERE locallyDeleted = 1")
    suspend fun getAllLocallyDeletedTasks(): List<LocalTasks>
}