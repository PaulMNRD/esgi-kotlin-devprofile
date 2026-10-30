package com.example.developerprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val p = getDevProfile();

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
        ProfileHeader(profile.name, profile.title)
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
fun ProfileHeader(name: String, title: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = title, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic)
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
fun ProfileCardPreview() {
    val p = getDevProfile()
    ProfileCard(p)
}

@Preview(showBackground = true)
@Composable
fun ProfileCardNotAvailablePreview() {
    val p = getExpertDevProfile()
    ProfileCard(p)
}

fun getDevProfile(): DevProfile {
    return DevProfile(
        "Paul Menard",
        "Développeur Web Full Stack",
        "Passionné par le web et le développement mobile.",
        listOf("Kotlin", "Compose"),
        "06 00 00 00 00",
        true,
    )
}

fun getExpertDevProfile(): DevProfile {
    return DevProfile(
        name = "Camille Martin",
        title = "Développeur Android Senior",
        desc = "10 ans d'expérience sur des applications Android natives.",
        skills = listOf("Java", "Android"),
        contact = "06 01 01 01 01",
        isAvailable = false
    )
}
data class DevProfile(
    val name: String,
    val title: String,
    val desc: String,
    val skills: List<String>,
    val contact: String,
    val isAvailable: Boolean,
)
