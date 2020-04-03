package co.icanteach.app.coronatracker.core

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(exception)
        is Resource.Loading -> Resource.Loading
    }
}

inline fun <T1, T2, R> Resource<T1>.combine(
    resource: Resource<T2>,
    transform: (T1, T2) -> R
): Resource<R> {
    return when {
        this is Resource.Success && resource is Resource.Success -> {
            Resource.Success(transform(data, resource.data))
        }
        this is Resource.Error -> Resource.Error(exception)
        resource is Resource.Error -> Resource.Error(resource.exception)
        else -> Resource.Loading
    }
}