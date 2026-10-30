package com.example.developerprofile

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
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
fun ProfileCard(profile: DevProfile, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) { // TODO: Background + Radius
        ProfileHeader(profile.name, profile.title)
        profile.skills.forEach { skill -> ProfileSkill(skill) }
        ContactButton(profile.isAvailable, profile.contact)
    }
}

@Composable
fun ProfileHeader(name: String, title: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = name) // TODO: Bold
        Text(text = title) // TODO: Italic + smaller
    }
}

@Composable
fun ProfileSkill(skill: String, modifier: Modifier = Modifier) {
    Text(text = skill) // TODO: List Element Style + smaller
}

@Composable
fun ContactButton(isAvailable: Boolean, contact: String, modifier: Modifier = Modifier) {
    Button(onClick = { /* TODO: On click swap text to contact */ }, modifier) { // TODO: button is disabled when isAvailable = false
        Text("Passer un appel")
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
        "TODO()",
        listOf("Kotlin", "Compose"),
        "06 00 00 00 00",
        true,
    )
}

fun getExpertDevProfile(): DevProfile {
    return DevProfile(
        name = "TODO()",
        title = "TODO()",
        desc = "TODO()",
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