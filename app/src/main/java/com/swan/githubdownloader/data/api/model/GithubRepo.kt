package com.swan.githubdownloader.data.api.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(val name: String,
                      @SerializedName("html_url") val htmlUrl: String,
                      val description: String?,
                      val language: String?,
                      @SerializedName("default_branch") val defaultBranch: String,
                      val owner: GitHubUser,
                      val private: Boolean
)
