package com.mohtdon.ui


sealed class UiState<out T> {

    data class Success<T>(var data: T?) : UiState<T>()

    data class Error(val message: String) : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    fun toData(): T? {
        return if (this is Success) {
            data
        } else {
            null
        }
    }

}