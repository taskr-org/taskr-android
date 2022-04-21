package live.taskr.taskr.utils.repository

import live.taskr.taskr.utils.database.User
import live.taskr.taskr.utils.networking.Result

interface TaskrRepo {
    suspend fun registerUser(user: User): Result<String>
    suspend fun loginUser(user: User): Result<String>
    suspend fun getUser(): Result<User>
    suspend fun logout(): Result<String>
}