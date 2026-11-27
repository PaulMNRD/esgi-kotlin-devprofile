package com.example.developerprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developerprofile.DeveloperProfileScreen

@Composable
fun ProfileContainer(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    DeveloperProfileScreen(
        uiState = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onToggleFavoriteFilter = viewModel::onToggleFavoriteFilter,
        onFavoriteClick = viewModel::onFavoriteClick,
        modifier = modifier,
    )
}
