package com.example.gitusers.ui.MainScreen

import com.example.gitusers.domain.model.Repo

data class RepoState(
    val repos: List<Repo>? = null,
    val isLoading: Boolean = false,
    val error:  String? = null
)
