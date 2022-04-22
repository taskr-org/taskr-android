package live.taskr.taskr.utils.networking

/**
 * It's a generic class acts as a wrapper to our network class
 */
sealed class Result<T>(
    val data:T? = null,
    val errorMessage:String? = null
) {
    class SUCCESS<T>(data: T? = null, errorMessage: String? = null):Result<T>(data, errorMessage)
    class ERROR<T>(errorMessage: String? = null, data: T? = null):Result<T>(data, errorMessage)
    class LOADING<T>:Result<T>()
}