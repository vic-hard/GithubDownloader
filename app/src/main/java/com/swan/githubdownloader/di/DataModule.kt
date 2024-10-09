package com.swan.githubdownloader.di

import com.swan.githubdownloader.data.repository.GithubRepository
import com.swan.githubdownloader.data.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    abstract fun bindGithubRepository(impl: GithubRepositoryImpl): GithubRepository

}