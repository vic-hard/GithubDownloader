package com.swan.githubdownloader.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swan.githubdownloader.data.database.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepository(repository: RepositoryEntity)

    @Query("SELECT * FROM downloaded_repositories ORDER BY id DESC")
    fun getAllRepositories(): Flow<List<RepositoryEntity>>
}