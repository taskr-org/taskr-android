package live.taskr.taskr.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import live.taskr.taskr.utils.Constants.AUTH_TOKEN_KEY


class SessionManager(val context:Context) {
    private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore("session_manager")

    suspend fun saveAuthToken(token: String) {
        val authToken = stringPreferencesKey(AUTH_TOKEN_KEY)
        context.dataStore.edit { preference ->
            preference[authToken] = token
        }
    }

    suspend fun getAuthToken():String? {
        val authTokenKey = stringPreferencesKey(AUTH_TOKEN_KEY)
        val preferences = context.dataStore.data.first()
        return preferences[authTokenKey]
    }
}