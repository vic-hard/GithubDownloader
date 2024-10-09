package com.swan.githubdownloader.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swan.githubdownloader.data.database.dao.RepositoryDao
import com.swan.githubdownloader.data.database.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}