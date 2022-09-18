package com.example.gitusers.data.navigation

import androidx.navigation.NavController
import com.example.gitusers.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}