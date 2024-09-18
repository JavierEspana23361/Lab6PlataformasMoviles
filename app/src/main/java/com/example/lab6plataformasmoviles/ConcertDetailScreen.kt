package com.example.lab6plataformasmoviles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ConcertDetailScreen(concertId: String?) {
    val concert = conciertos.firstOrNull { it.id == concertId } ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = concert.imageResId),
            contentDescription = concert.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )
        Text(text = concert.title, style = MaterialTheme.typography.titleLarge)
        Text(text = concert.location, style = MaterialTheme.typography.bodyMedium)
        Text(text = concert.date, style = MaterialTheme.typography.bodyMedium)
        Text(text = concert.description, style = MaterialTheme.typography.bodyMedium)
    }
}

