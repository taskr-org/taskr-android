package live.taskr.taskr.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import live.taskr.taskr.utils.Constants.AUTH_TOKEN_KEY
import live.taskr.taskr.utils.Constants.EMAIL_KEY
import live.taskr.taskr.utils.Constants.FULLNAME_KEY
import live.taskr.taskr.utils.Constants.USERNAME_KEY


class SessionManager(val context: Context) {
    private val Context.dataStore: DataStore<Preferences>
            by preferencesDataStore("session_manager")

    suspend fun authSession(fullName: String, userName: String, email: String, token: String) {
        val fullNameKey = stringPreferencesKey(FULLNAME_KEY)
        val userNameKey = stringPreferencesKey(USERNAME_KEY)
        val emailKey = stringPreferencesKey(EMAIL_KEY)
        val authToken = stringPreferencesKey(AUTH_TOKEN_KEY)
        context.dataStore.edit { preference ->
            preference[fullNameKey] = fullName
            preference[userNameKey] = userName
            preference[emailKey] = email
            preference[authToken] = token
        }
    }

    suspend fun loginSession(userName: String, email: String, token: String) {
        val userNameKey = stringPreferencesKey(USERNAME_KEY)
        val emailKey = stringPreferencesKey(EMAIL_KEY)
        val authToken = stringPreferencesKey(AUTH_TOKEN_KEY)

        context.dataStore.edit { preference ->
            preference[userNameKey] = userName
            preference[emailKey] = email
            preference[authToken] = token
        }
    }

    suspend fun getAuthToken(): String? {
        val authTokenKey = stringPreferencesKey(AUTH_TOKEN_KEY)
        val preferences = context.dataStore.data.first()
        return preferences[authTokenKey]
    }

    suspend fun getCurrentUserName(): String? {
        val userNameKey = stringPreferencesKey(USERNAME_KEY)
        val preference = context.dataStore.data.first()
        return preference[userNameKey]
    }

    suspend fun getCurrentUserEmail(): String? {
        val emailKey = stringPreferencesKey(EMAIL_KEY)
        val preference = context.dataStore.data.first()
        return preference[emailKey]
    }

    suspend fun logOut() {
        context.dataStore.edit { dataStore ->
            dataStore.clear()
        }
    }
}