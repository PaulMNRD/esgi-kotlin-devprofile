package com.example.developerprofile.data

data class Project(
    val name: String,
    val description: String,
    val technologies: String,
)

data class DevProfile(
    val name: String,
    val title: String,
    val desc: String,
    val skills: List<String>,
    val projects: List<Project>,
    val contact: String,
    val email: String,
    val location: String,
    val isAvailable: Boolean,
)

fun getDevProfile(): DevProfile {
    return DevProfile(
        name = "Paul Menard",
        title = "Full Stack Web Developer",
        desc = "Passionate about web and mobile development.",
        skills = listOf("Kotlin", "Jetpack Compose", "TypeScript", "React", "Node.js", "SQL"),
        projects = listOf(
            Project(
                name = "Developer Profile",
                description = "Android app that displays a developer profile card.",
                technologies = "Kotlin, Compose",
            ),
            Project(
                name = "Task Manager",
                description = "Web app to organize daily tasks in a team.",
                technologies = "React, Node.js",
            ),
            Project(
                name = "Weather Dashboard",
                description = "Dashboard showing the weather of several cities.",
                technologies = "TypeScript, SQL",
            ),
        ),
        contact = "06 00 00 00 00",
        email = "paul.menard@example.com",
        location = "Nantes, France",
        isAvailable = true,
    )
}

fun getExpertDevProfile(): DevProfile {
    return DevProfile(
        name = "Camille Martin",
        title = "Senior Android Developer",
        desc = "10 years of experience on native Android apps.",
        skills = listOf("Java", "Kotlin", "Android SDK"),
        projects = listOf(
            Project(
                name = "Banking App",
                description = "Mobile banking app used by thousands of customers.",
                technologies = "Kotlin, Android SDK",
            ),
        ),
        contact = "06 01 01 01 01",
        email = "camille.martin@example.com",
        location = "Lyon, France",
        isAvailable = false,
    )
}
