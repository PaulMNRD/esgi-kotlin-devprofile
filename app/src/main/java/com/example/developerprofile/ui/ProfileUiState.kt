package com.example.developerprofile.ui

import com.example.developerprofile.data.DevProfile
import com.example.developerprofile.data.Project

data class ProfileUiState(
    val profile: DevProfile,
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val favoriteProjectIds: Set<Int> = emptySet(),
    val projects: List<Project> = profile.projects,
)
