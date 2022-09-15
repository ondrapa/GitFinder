package com.example.gitusers.util

sealed class UiEvent {

    data class ShowSnackbar(
        val message: String
    ): UiEvent()

}