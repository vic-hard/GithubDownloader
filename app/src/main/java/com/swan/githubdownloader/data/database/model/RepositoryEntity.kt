package com.swan.githubdownloader.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_repositories")
data class RepositoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val repoName: String
)
