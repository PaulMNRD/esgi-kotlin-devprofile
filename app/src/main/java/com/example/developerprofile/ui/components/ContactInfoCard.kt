package com.example.developerprofile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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
