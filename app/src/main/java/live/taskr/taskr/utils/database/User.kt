package live.taskr.taskr.utils.database

import java.io.Serializable

data class User(
    val fullName: String? = null,
    val username: String? = null,
    val email: String? = null,
    val password: String? = null
): Serializable
