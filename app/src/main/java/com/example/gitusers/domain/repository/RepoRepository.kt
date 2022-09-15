package com.example.gitusers.domain.repository

import com.example.gitusers.domain.model.Repo
import com.example.gitusers.util.Resource

interface RepoRepository {
    suspend fun getRepo(user: String): Resource<List<Repo>>
}