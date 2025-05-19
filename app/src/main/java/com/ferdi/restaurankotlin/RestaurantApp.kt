package com.ferdi.restaurankotlin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferdi.restaurankotlin.data.model.dummyMenu
import com.ferdi.restaurankotlin.navigation.Screen

@Composable
fun RestaurantApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.home.route
    ) {
        composable(Screen.home.route) {
            HomeScreen(
                navigateToDetail = { index ->
                    navController.navigate(Screen.detail.createRoute(index = index))
                },
                navigateToCart = {
                    navController.navigate(Screen.cart.route)
                }
            )
        }

        composable(
            Screen.detail.route,
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: return@composable
            val menu = dummyMenu[index]
            DetailMenuScreen(
                data = menu,
                navController = navController
            )
        }
        composable(Screen.cart.route) {
            CartScreen(navController)
        }
    }
}