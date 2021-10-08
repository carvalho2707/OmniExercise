package pt.tiagocarvalho.omni.model

sealed class Result<out T, out E> {
    data class Success<T, E>(val value: T) : Result<T, E>()
    data class Failure<T, E>(val error: E) : Result<T, E>()
}

inline fun <T, E> Result<T, E>.fold(onSuccess: (T) -> Unit, onFailure: (E) -> Unit) {
    when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onFailure(error)
    }
}

inline val <T, E> Result<T, E>.isSuccess: Boolean
    get() = this is Result.Success

inline val <T, E> Result<T, E>.isFailure: Boolean
    get() = this is Result.Failure
