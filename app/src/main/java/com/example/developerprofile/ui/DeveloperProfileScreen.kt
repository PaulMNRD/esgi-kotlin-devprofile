package com.example.developerprofile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.developerprofile.data.getDevProfile
import com.example.developerprofile.data.getExpertDevProfile
import com.example.developerprofile.ui.components.ActionButtons
import com.example.developerprofile.ui.components.ContactInfoCard
import com.example.developerprofile.ui.components.ProfileDescription
import com.example.developerprofile.ui.components.ProfileHeader
import com.example.developerprofile.ui.components.ProjectCard
import com.example.developerprofile.ui.components.ProjectFilterRow
import com.example.developerprofile.ui.components.ProjectSearchField
import com.example.developerprofile.ui.components.SkillsSection
import com.example.developerprofile.ui.theme.DeveloperProfileTheme

@Composable
fun DeveloperProfileScreen(
    uiState: ProfileUiState,
    onSearchQueryChange: (String) -> Unit,
    onToggleFavoriteFilter: () -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        ProfileContent(
            uiState = uiState,
            onSearchQueryChange = onSearchQueryChange,
            onToggleFavoriteFilter = onToggleFavoriteFilter,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

@Composable
fun ProfileContent(
    uiState: ProfileUiState,
    onSearchQueryChange: (String) -> Unit,
    onToggleFavoriteFilter: () -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val profile = uiState.profile

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { ProfileHeader(profile.name, profile.title, profile.isAvailable) }
        item { ProfileDescription(profile.desc) }
        item {
            ContactInfoCard(
                email = profile.email,
                phone = profile.contact,
                location = profile.location,
                isAvailable = profile.isAvailable,
            )
        }
        item { SkillsSection(profile.skills) }
        item { Text(text = "Projects", style = MaterialTheme.typography.titleMedium) }
        item { ProjectSearchField(uiState.searchQuery, onSearchQueryChange) }
        item {
            ProjectFilterRow(
                showOnlyFavorites = uiState.showOnlyFavorites,
                projectCount = uiState.projects.size,
                onToggleFavoriteFilter = onToggleFavoriteFilter,
            )
        }
        items(uiState.projects, key = { project -> project.id }) { project ->
            ProjectCard(
                project = project,
                isFavorite = project.id in uiState.favoriteProjectIds,
                onFavoriteClick = { onFavoriteClick(project.id) },
            )
        }
        if (uiState.projects.isEmpty()) {
            item {
                Text(
                    text = "No project found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        item {
            ActionButtons(
                isAvailable = profile.isAvailable,
                onContactClick = {},
                onPortfolioClick = {},
            )
        }
    }
}

@Preview(name = "Light theme", showBackground = true, heightDp = 1100)
@Composable
fun DeveloperProfileScreenPreview() {
    DeveloperProfileTheme {
        DeveloperProfileScreen(
            uiState = ProfileUiState(profile = getDevProfile()),
            onSearchQueryChange = {},
            onToggleFavoriteFilter = {},
            onFavoriteClick = {},
        )
    }
}

@Preview(name = "Dark theme", showBackground = true, heightDp = 1100)
@Composable
fun DarkDeveloperProfileScreenPreview() {
    DeveloperProfileTheme(darkTheme = true) {
        DeveloperProfileScreen(
            uiState = ProfileUiState(profile = getDevProfile()),
            onSearchQueryChange = {},
            onToggleFavoriteFilter = {},
            onFavoriteClick = {},
        )
    }
}

@Preview(name = "Not available", showBackground = true, heightDp = 900)
@Composable
fun NotAvailableProfileScreenPreview() {
    DeveloperProfileTheme {
        DeveloperProfileScreen(
            uiState = ProfileUiState(profile = getExpertDevProfile()),
            onSearchQueryChange = {},
            onToggleFavoriteFilter = {},
            onFavoriteClick = {},
        )
    }
}