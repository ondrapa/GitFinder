package com.example.gitusers.data.remote

import com.example.gitusers.domain.model.Branch


data class BranchDto(
    val name: String,
    val commit: Commit,
    val `protected`: Boolean
) {
    fun toBranch(): Branch {
        return Branch(name)
    }
}

data class Commit(
    val sha: String,
    val url: String
)