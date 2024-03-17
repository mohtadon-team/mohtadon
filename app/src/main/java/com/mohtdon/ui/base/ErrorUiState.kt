package com.mohtdon.ui.base

open class ErrorUiState(val message: String = "Error occurred!")

class NullResultErrorState(message: String) : ErrorUiState(message)
class NetworkErrorState(message: String) : ErrorUiState(message)