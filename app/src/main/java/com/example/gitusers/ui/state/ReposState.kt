package com.example.gitusers.ui.state

import com.example.gitusers.domain.model.Repo

data class ReposState(
    val repos: List<Repo>? = null,
    val isLoading: Boolean = false,
    val error:  String? = null
)
