package live.taskr.taskr.utils.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import live.taskr.taskr.utils.database.local.LocalHabits

@Dao
interface HabitsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habits: LocalHabits)

    @Query("DELETE FROM localhabits WHERE habitId =:habitId")
    suspend fun deleteHabit(habitId: String)

    @Query("UPDATE localhabits SET locallyDeleted = 1 WHERE habitId = :habitId")
    suspend fun deleteHabitLocally(habitId: String)

    @Query("SELECT * FROM localhabits WHERE isOnline = 0")
    suspend fun getLocalHabits(): List<LocalHabits>

    @Query("SELECT * FROM localhabits WHERE locallyDeleted = 1")
    suspend fun getAllLocallyDeletedHabits(): List<LocalHabits>
}