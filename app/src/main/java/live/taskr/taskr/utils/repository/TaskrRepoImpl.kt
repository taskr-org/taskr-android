package live.taskr.taskr.utils.repository

import live.taskr.taskr.utils.networking.Result
import live.taskr.taskr.utils.SessionManager
import live.taskr.taskr.utils.database.TaskrApi
import live.taskr.taskr.utils.database.User
import live.taskr.taskr.utils.database.local.dao.HabitsDao
import live.taskr.taskr.utils.database.local.dao.TasksDao
import javax.inject.Inject
//@Inject cons
class TaskrRepoImpl (
    val taskrApi: TaskrApi,
    val tasksDao: TasksDao,
    val habitsDao: HabitsDao,
    val sessionManager: SessionManager,
) : TaskrRepo {
    override suspend fun registerUser(user: User): Result<String> {
        return try {
            val result = taskrApi.registerUser(user)
            if (result.success) {
                sessionManager.authSession(
                    token = result.message,
                    fullName = user.fullName ?: "",
                    userName = user.username ?: "",
                    email = user.email ?: ""
                )
                Result.SUCCESS("User Created")
            } else {
                Result.ERROR(result.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.ERROR(e.message ?: "Some Problem Occurred")
        }
    }

    override suspend fun loginUser(user: User): Result<String> {
        return try {
            val result = taskrApi.loginUser(user)
            if (result.success) {
                sessionManager.loginSession(
                    token = result.message,
                    userName = user.username ?: "",
                    email = user.email ?: ""
                )
                Result.SUCCESS("Login success")
            } else {
                Result.ERROR(result.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.ERROR(e.message ?: "Some Problem Occurred")
        }
    }

    override suspend fun getUser(): Result<User> {
        return try {
            val userName = sessionManager.getCurrentUserName()
            val email = sessionManager.getCurrentUserEmail()
            if (userName == null || email == null) {
                Result.ERROR<User>("User not logged in")
            }
            Result.SUCCESS(User(
                username = userName,
                email = email,
            ))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.ERROR(e.message ?: "Some Problem Occurred")
        }
    }

    override suspend fun logout(): Result<String> {
        return try {
            sessionManager.logOut()
            Result.SUCCESS("Logout success")
        } catch (e: Exception) {
            e.printStackTrace()
            Result.ERROR(e.message ?: "Some Problem Occurred")
        }
    }
}