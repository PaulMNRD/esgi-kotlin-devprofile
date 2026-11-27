package com.example.developerprofile.ui

import androidx.lifecycle.ViewModel
import com.example.developerprofile.data.Project
import com.example.developerprofile.data.getDevProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val profile = getDevProfile()

    private val _uiState = MutableStateFlow(ProfileUiState(profile = profile))

    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        updateState(_uiState.value.copy(searchQuery = query))
    }

    fun onToggleFavoriteFilter() {
        val currentState = _uiState.value
        updateState(currentState.copy(showOnlyFavorites = !currentState.showOnlyFavorites))
    }

    fun onFavoriteClick(projectId: Int) {
        val favorites = _uiState.value.favoriteProjectIds
        val newFavorites = if (projectId in favorites) favorites - projectId else favorites + projectId
        updateState(_uiState.value.copy(favoriteProjectIds = newFavorites))
    }

    private fun updateState(newState: ProfileUiState) {
        _uiState.value = newState.copy(projects = filterProjects(newState))
    }

    private fun filterProjects(state: ProfileUiState): List<Project> {
        val query = state.searchQuery.trim()

        return profile.projects.filter { project ->
            val matchesSearch = query.isEmpty() ||
                project.name.contains(query, ignoreCase = true) ||
                project.description.contains(query, ignoreCase = true) ||
                project.technologies.contains(query, ignoreCase = true)
            val matchesFavorites = !state.showOnlyFavorites || project.id in state.favoriteProjectIds

            matchesSearch && matchesFavorites
        }
    }
}
