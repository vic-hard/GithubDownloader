package com.swan.githubdownloader.di

import android.content.ContentResolver
import android.content.Context
import com.swan.githubdownloader.data.repository.FileRepository
import com.swan.githubdownloader.data.repository.FileRepositoryImpl
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
    ): FileRepository {
        return FileRepositoryImpl(context)
    }

}