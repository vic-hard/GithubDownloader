package com.swan.githubdownloader.di

import android.app.DownloadManager
import android.content.Context
import androidx.room.Room
import com.swan.githubdownloader.DownloadReceiver
import com.swan.githubdownloader.data.api.GithubApiService
import com.swan.githubdownloader.data.database.AppDatabase
import com.swan.githubdownloader.data.database.dao.RepositoryDao
import com.swan.githubdownloader.data.repository.FileRepository
import com.swan.githubdownloader.data.repository.FileRepositoryImpl
import com.swan.githubdownloader.data.repository.GithubRepository
import com.swan.githubdownloader.data.repository.GithubRepositoryImpl
import com.swan.githubdownloader.domain.downloads.SaveDownloadedRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideFileRepository(
        @ApplicationContext context: Context,
        downloadManager: DownloadManager
    ): FileRepository {
        return FileRepositoryImpl(context, downloadManager)
    }

    @Provides
    @Singleton
    fun provideGithubRepository(
        githubApiService: GithubApiService,
        repositoryDao: RepositoryDao
    ): GithubRepository {
        return GithubRepositoryImpl(githubApiService, repositoryDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepositoryDao(database: AppDatabase): RepositoryDao {
        return database.repositoryDao()
    }

    @Provides
    @Singleton
    fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager {
        return context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    @Provides
    @Singleton
    fun provideDownloadReceiver(downloadManager: DownloadManager,
                                useCase: SaveDownloadedRepoUseCase
    ): DownloadReceiver {
        return DownloadReceiver(downloadManager, useCase)
    }
}