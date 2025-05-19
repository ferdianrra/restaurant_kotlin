package com.ferdi.restaurankotlin.navigation

sealed class Screen(val route: String){
    object home: Screen("home")
    object detail: Screen("detail/{index}") {
        fun createRoute(index: Int) ="detail/$index"
    }
    object cart: Screen("cart")
}