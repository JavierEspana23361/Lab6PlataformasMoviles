package com.example.lab6plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab6plataformasmoviles.ui.theme.InfoConciertoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoConciertoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "concert_list",
        modifier = modifier
    ) {
        composable("concert_list") { ConcertListScreen(navController) }
        composable("profile") { ProfileScreen() }
        composable("favorites") { ConcertInfoScreen(navController) }
        composable("concert_detail/{concertId}") { backStackEntry ->
            val concertId = backStackEntry.arguments?.getString("concertId")
            if (concertId != null) {
                ConcertDetailScreen(concertId = concertId)
            }
        }
    }
}