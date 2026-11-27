package com.example.developerprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.developerprofile.data.DevProfile
import com.example.developerprofile.data.Project
import com.example.developerprofile.data.getDevProfile
import com.example.developerprofile.data.getExpertDevProfile
import com.example.developerprofile.ui.theme.DeveloperProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeveloperProfileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeveloperProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun DeveloperProfileScreen(
    modifier: Modifier = Modifier,
    profile: DevProfile = getDevProfile(),
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        ProfileContent(profile)
    }
}


@Composable
fun ProfileContent(
    profile: DevProfile,
    modifier: Modifier = Modifier,
) {
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
        items(profile.projects) { project ->
            ProjectCard(project)
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

@Composable
fun ProfileHeader(
    name: String,
    title: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileAvatar(isAvailable)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
fun ProfileAvatar(isAvailable: Boolean, modifier: Modifier = Modifier) {
    val badgeColor = if (isAvailable) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline

    Box(modifier = modifier.size(72.dp)) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile picture",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(12.dp),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(18.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer, CircleShape)
                .padding(2.dp)
                .background(badgeColor, CircleShape),
        )
    }
}

@Composable
fun ContactInfoCard(
    email: String,
    phone: String,
    location: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier,
) {
    var showDetails by rememberSaveable { mutableStateOf(false) }

    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = "Contact", style = MaterialTheme.typography.titleMedium)
            TextButton(onClick = { showDetails = !showDetails }) {
                Text(if (showDetails) "Hide contact info" else "Show contact info")
            }
            if (showDetails) {
                InfoField(label = "Email", value = email, icon = Icons.Default.Email)
                InfoField(label = "Phone", value = phone, icon = Icons.Default.Phone)
                InfoField(label = "Location", value = location, icon = Icons.Default.LocationOn)
                InfoField(
                    label = "Availability",
                    value = if (isAvailable) "Available for new projects" else "Not available",
                    icon = if (isAvailable) Icons.Default.CheckCircle else Icons.Default.Close,
                )
            }
        }
    }
}

@Composable
fun InfoField(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
fun ProfileDescription(desc: String, modifier: Modifier = Modifier) {
    Text(text = desc, modifier = modifier, style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun SkillsSection(skills: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Skills", style = MaterialTheme.typography.titleMedium)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(skills) { skill ->
                SkillChip(skill)
            }
        }
    }
}

@Composable
fun SkillChip(skill: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        )
    }
}

@Composable
fun ProjectCard(project: Project, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = project.name, style = MaterialTheme.typography.titleSmall)
                Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = project.technologies,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
fun ActionButtons(
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onPortfolioClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(
            onClick = onContactClick,
            enabled = isAvailable,
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
            Text("Contact")
        }
        OutlinedButton(
            onClick = onPortfolioClick,
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
            Text("Portfolio")
        }
    }
}

@Preview(name = "Light theme", showBackground = true, heightDp = 1100)
@Composable
fun DeveloperProfileScreenPreview() {
    DeveloperProfileTheme {
        DeveloperProfileScreen()
    }
}

@Preview(name = "Dark theme", showBackground = true, heightDp = 1100)
@Composable
fun DarkDeveloperProfileScreenPreview() {
    DeveloperProfileTheme(darkTheme = true) {
        DeveloperProfileScreen()
    }
}

@Preview(name = "Not available", showBackground = true, heightDp = 900)
@Composable
fun NotAvailableProfileScreenPreview() {
    DeveloperProfileTheme {
        DeveloperProfileScreen(profile = getExpertDevProfile())
    }
}