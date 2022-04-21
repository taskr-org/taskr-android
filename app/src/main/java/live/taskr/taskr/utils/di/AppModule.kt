package live.taskr.taskr.utils.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import live.taskr.taskr.utils.Constants.BASE_URL
import live.taskr.taskr.utils.SessionManager
import live.taskr.taskr.utils.database.TaskrApi
import live.taskr.taskr.utils.database.TaskrDatabase
import live.taskr.taskr.utils.database.local.dao.HabitsDao
import live.taskr.taskr.utils.database.local.dao.TasksDao
import live.taskr.taskr.utils.repository.TaskrRepo
import live.taskr.taskr.utils.repository.TaskrRepoImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context)

    @Singleton
    @Provides
    fun provideTaskrDatabase(
        @ApplicationContext context: Context
    ): TaskrDatabase = Room.databaseBuilder(
        context,
        TaskrDatabase::class.java,
        "taskr_db"
    ).build()

    @Singleton
    @Provides
    fun provideTasksDao(
        tasksDb: TaskrDatabase
    ) = tasksDb.getTasksDao()

    @Singleton
    @Provides
    fun provideHabitsDao(
        habitsBd: TaskrDatabase
    ) = habitsBd.getHabitsDao()

    @Singleton
    @Provides
    fun providesTaskrRepo(
        taskrApi: TaskrApi,
        tasksDao: TasksDao,
        habitsDao: HabitsDao,
        sessionManager: SessionManager
    ): TaskrRepo {
        return TaskrRepoImpl(
            taskrApi,
            tasksDao,
            habitsDao,
            sessionManager
        )
    }

    @Singleton
    @Provides
    fun provideTaskrApi(): TaskrApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskrApi::class.java)
    }
}