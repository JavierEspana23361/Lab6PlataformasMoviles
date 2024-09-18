package com.example.lab6plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab6plataformasmoviles.ui.theme.InfoConciertoTheme


class ConcertInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfoConciertoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConcertInfoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConcertInfoScreen(modifier: Modifier = Modifier) {
    val concertsList = remember { mutableStateListOf(*conciertos.toTypedArray()) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Sección de Favoritos
        item {
            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        val favoriteConcerts = concertsList.filter { it.isFavorite }
        if (favoriteConcerts.isNotEmpty()) {
            itemsIndexed(favoriteConcerts.chunked(2)) { _, pair ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    pair.forEach { concert ->
                        ConcertCard(
                            concert = concert,
                            modifier = Modifier.weight(1f),
                            onFavoriteClick = {
                                concert.isFavorite = !concert.isFavorite
                            }
                        )
                    }
                }
            }
        } else {
            item {
                Text(
                    text = "No hay conciertos favoritos",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Sección de Todos los Conciertos
        item {
            Text(
                text = "Todos los Conciertos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        itemsIndexed(concertsList.chunked(2)) { _, pair ->
            Row(modifier = Modifier.fillMaxWidth()) {
                pair.forEach { concert ->
                    ConcertCard(
                        concert = concert,
                        modifier = Modifier.weight(1f),
                        onFavoriteClick = {
                            concert.isFavorite = !concert.isFavorite
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concierto, modifier: Modifier = Modifier, onFavoriteClick: () -> Unit) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = concert.imageResId),
                contentDescription = concert.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = concert.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = concert.date,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            IconButton(onClick = onFavoriteClick, modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = if (concert.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (concert.isFavorite) "Eliminar de favoritos" else "Añadir a favoritos"
                )
            }
        }
    }
}
