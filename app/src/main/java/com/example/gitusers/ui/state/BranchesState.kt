package com.example.gitusers.ui.state

import com.example.gitusers.domain.model.Branch

data class BranchesState(
    val branches: List<Branch>?,
    val isLoading: Boolean = false,
    val error:  String? = null
)
