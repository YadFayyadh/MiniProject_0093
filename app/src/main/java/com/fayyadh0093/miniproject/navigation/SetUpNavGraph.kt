package com.fayyadh0093.miniproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fayyadh0093.miniproject.model.daftarResep
import com.fayyadh0093.miniproject.ui.theme.screen.AboutScreen
import com.fayyadh0093.miniproject.ui.theme.screen.AddRecipeScreen
import com.fayyadh0093.miniproject.ui.theme.screen.DetailScreen
import com.fayyadh0093.miniproject.ui.theme.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }

        composable(route = "${Screen.Detail.route}/{namaResep}") { backStackEntry ->
            val namaResep = backStackEntry.arguments?.getString("namaResep") ?: ""
            DetailScreen(namaResep = namaResep, navController = navController)
        }

        composable(route = Screen.Tambah.route) {
            AddRecipeScreen(navController) { resepBaru ->
                daftarResep.add(resepBaru)
            }
        }


    }
}