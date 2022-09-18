package com.example.gitusers.domain.repository

import com.example.gitusers.domain.model.Branch
import com.example.gitusers.domain.model.CommitModel
import com.example.gitusers.domain.model.Repo
import com.example.gitusers.util.Resource

interface RepoRepository {

    suspend fun getRepos(user: String): Resource<List<Repo>>

    suspend fun getBranches(owner: String, repo: String): Resource<List<Branch>>

    suspend fun getCommits(owner: String, repo: String): Resource<List<CommitModel>>

}