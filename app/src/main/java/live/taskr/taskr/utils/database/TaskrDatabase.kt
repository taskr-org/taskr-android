package live.taskr.taskr.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import live.taskr.taskr.utils.database.local.LocalHabits
import live.taskr.taskr.utils.database.local.LocalTasks
import live.taskr.taskr.utils.database.local.dao.HabitsDao
import live.taskr.taskr.utils.database.local.dao.TasksDao

@Database(
    entities = [LocalTasks::class ,LocalHabits::class],
    version = 1,
    exportSchema = false
)
abstract class TaskrDatabase: RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    abstract fun getHabitsDao(): HabitsDao
}