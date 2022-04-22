package live.taskr.taskr.utils.database

import java.io.Serializable

data class User(
    val fullname: String? = null,
    val username: String? = null,
    val email: String? = null,
    val password: String? = null
): Serializable
