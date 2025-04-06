package com.fayyadh0093.miniproject.navigation

sealed class Screen (val route : String){
    object Home : Screen("main")
    object About : Screen("about")
    object Detail : Screen("detail")
    object Tambah : Screen("tambah")

}