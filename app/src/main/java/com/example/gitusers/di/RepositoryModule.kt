package com.example.gitusers.di

import com.example.gitusers.data.repository.RepoRepositoryImp
import com.example.gitusers.domain.repository.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepoRepository(
        repoRepositoryImp: RepoRepositoryImp
    ): RepoRepository

}