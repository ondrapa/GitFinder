package com.example.gitusers.data.repository

import android.content.res.Resources
import com.example.gitusers.data.remote.RepoApi
import com.example.gitusers.domain.model.Repo
import com.example.gitusers.domain.repository.RepoRepository
import com.example.gitusers.util.Resource
import retrofit2.HttpException
import javax.inject.Inject

class RepoRepositoryImp @Inject constructor(
    private val api: RepoApi
): RepoRepository {

    override suspend fun getRepo(user: String): Resource<List<Repo>> {
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
}