package pt.tiagocarvalho.omni.model

public sealed class Result<out T, out E> {
    public data class Success<T, E>(val value: T) : Result<T, E>()
    public data class Failure<T, E>(val error: E) : Result<T, E>()
}

public inline fun <T, E> Result<T, E>.fold(onSuccess: (T) -> Unit, onFailure: (E) -> Unit) {
    when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onFailure(error)
    }
}
