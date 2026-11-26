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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.developerprofile.data.DevProfile
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
fun DeveloperProfileScreen(modifier: Modifier = Modifier) {
    val p = getDevProfile()

    Surface(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        ProfileCard(p)
    }
}


@Composable
fun ProfileCard(
    profile: DevProfile,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        ProfileHeader(profile.name, profile.title, profile.isAvailable)
        ProfileDescription(profile.desc)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            profile.skills.forEach { skill -> ProfileSkill(skill) }
        }
        ContactButton(
            isAvailable = profile.isAvailable,
            contact = profile.contact,
            modifier = Modifier.fillMaxWidth(),
        )
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

// Placeholder avatar with a small badge showing if the developer is available
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
fun ProfileDescription(desc: String, modifier: Modifier = Modifier) {
    Text(text = desc, modifier = modifier, style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun ProfileSkill(skill: String, modifier: Modifier = Modifier) {
    Text(text = "• $skill", modifier = modifier.padding(start = 8.dp), style = MaterialTheme.typography.bodySmall)
}

@Composable
fun ContactButton(
    isAvailable: Boolean,
    contact: String,
    modifier: Modifier = Modifier,
) {
    Button(onClick = {}, modifier, enabled = isAvailable) {
        Text("Passer un appel: $contact")
    }
}

@Preview(showBackground = true)
@Composable
fun DeveloperProfileScreenPreview() {
    DeveloperProfileTheme {
        DeveloperProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardNotAvailablePreview() {
    val p = getExpertDevProfile()
    DeveloperProfileTheme {
        ProfileCard(p)
    }
}

@Preview(showBackground = true)
@Composable
fun DarkDeveloperProfileScreenPreview() {
    DeveloperProfileTheme(darkTheme = true) {
        DeveloperProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkProfileCardNotAvailablePreview() {
    val p = getExpertDevProfile()
    DeveloperProfileTheme(darkTheme = true) {
        ProfileCard(p)
    }
}