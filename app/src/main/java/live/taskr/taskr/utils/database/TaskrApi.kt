package live.taskr.taskr.utils.database

import live.taskr.taskr.utils.database.remote.RemoteHabits
import live.taskr.taskr.utils.database.remote.RemoteTasks
import retrofit2.http.*

interface TaskrApi {

    /**
     *  Auth
     */
    @Headers("Content-Type: application/json")
    @POST("/users/register")
    suspend fun registerUser(
        @Body user: User
    ): Responses

    @Headers("Content-Type: application/json")
    @POST("/users/login")
    suspend fun loginUser(
        @Body user: User
    ): Responses

    @Headers("Content-Type: application/json")
    @POST("/users/gauth")
    suspend fun createGauth(
        @Body user: User
    ): Responses

    /**
     *  Tasks
     */
    @Headers("Content-Type: application/json")
    @POST("/task/new")
    suspend fun CreateTask(
        @Header("Authorization") token: String,
        @Body task: RemoteTasks
    ): Responses

    @Headers("Content-Type: application/json")
    @GET("/task/getall")
    suspend fun GetAllTasks(
        @Header("Authorization") token: String
    ): List<RemoteTasks>

    @Headers("Content-Type: application/json")
    @GET("/task/get/:id")
    suspend fun GetTask(
        @Header("Authorization") token: String,
        @Body task: RemoteTasks
    ): Responses

    @Headers("Content-Type: application/json")
    @DELETE("/task/delete")
    suspend fun DeleteTask(
        @Header("Authorization") token: String,
        @Query("id") taskId: String
    ): Responses

    /**
     *  Habits
     */
    @Headers("Content-Type: application/json")
    @POST("/habit/new")
    suspend fun CreateHabit(
        @Header("Authorization") token: String,
        @Body habit: RemoteHabits
    ): Responses

    @Headers("Content-Type: application/json")
    @GET("/habit/getall")
    suspend fun GetAllHabits(
        @Header("Authorization")
        @Body habit: RemoteHabits
    ): List<RemoteHabits>

    @Headers("Content-Type: application/json")
    @GET("/habit/get/:id")
    suspend fun GetHabit(
        @Header("Authorization") token: String,
        @Body habit: RemoteHabits
    ): Responses

    @Headers("Content-Type: application/json")
    @DELETE("/habit/delete")
    suspend fun DeleteHabit(
        @Header("Authorization") token: String,
        @Query("id") habitId: String
    ): Responses
}