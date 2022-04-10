package live.taskr.taskr.utils.networking

/**
 * It's a generic class acts as a wrapper to our network class
 */
sealed class Result<T>(
    val data:T? = null,
    val errorMessage:String? = null
) {
    class SUCCESS<T>(data: T?, errorMessage: String? = null):Result<T>(data, errorMessage)
    class ERROR<T>(errorMessage: String?, data: T?):Result<T>(data, errorMessage)
    class LOADING<T>:Result<T>()
}