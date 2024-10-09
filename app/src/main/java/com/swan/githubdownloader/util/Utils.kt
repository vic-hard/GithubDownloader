package com.swan.githubdownloader.util

fun extractUserNameFromGithubUrl(url: String): String {
    val parts = url.split("/")
    return if (parts.size > 4) parts[3] else "User"
}