package com.example.gitusers.ui.state

import com.example.gitusers.domain.model.Branch
import com.example.gitusers.domain.model.CommitModel

data class CommitState(
    val commits: List<CommitModel>?,
    val isLoading: Boolean = false,
    val error:  String? = null
)
