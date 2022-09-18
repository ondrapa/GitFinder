package com.example.gitusers.data.repository

import com.example.gitusers.data.remote.Api
import com.example.gitusers.domain.model.Branch
import com.example.gitusers.domain.model.CommitModel
import com.example.gitusers.domain.model.Repo
import com.example.gitusers.domain.repository.RepoRepository
import com.example.gitusers.util.Resource
import retrofit2.HttpException
import javax.inject.Inject

class RepoRepositoryImp @Inject constructor(
    private val api: Api
): RepoRepository {

    override suspend fun getRepos(user: String): Resource<List<Repo>> {
        return try {
            Resource.Success(
                data = api.getRepo(user).map { it.toRepo() }
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            if (e.code() == 404) {
                Resource.Error("This user is not on GitHub.")
            } else {
                Resource.Error("An unknown error occurred.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An unknown error occurred.")
        }
    }

    override suspend fun getBranches(owner: String, repo: String): Resource<List<Branch>> {
        return try {
            Resource.Success(
                data = api.getBranches(owner, repo).map { it.toBranch() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getCommits(owner: String, repo: String): Resource<List<CommitModel>> {
        return try {
            Resource.Success(
                data = api.getLatestCommits(owner, repo).map { it.toCommit() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}