package br.senai.sp.saveeats.menubarcomponents.screen


import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.homecomponents.screen.HomeScreen
import br.senai.sp.saveeats.ordercomponents.screen.OrderScreen
import br.senai.sp.saveeats.profilecomponents.screen.ProfileScreen
import br.senai.sp.saveeats.recipecomponents.screen.RecipeScreen
import br.senai.sp.saveeats.recipestipscomponents.screen.RecipesTipsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navController2: NavHostController,
    lifecycle: LifecycleCoroutineScope,
    viewModel: ViewModel
) {
    NavHost(

        navController = navController,
        startDestination = MenuBar.Home.route

    ){

        composable(route = MenuBar.Home.route) {
            HomeScreen(navController = navController, lifecycle = lifecycle, localStorage = Storage())
        }

        composable(route = MenuBar.Orders.route) {
            OrderScreen(navController, navController2, Storage())
        }

        composable(route = MenuBar.Recipes.route) {
            RecipesTipsScreen(navController, localStorage = Storage())
        }

        composable(route = MenuBar.Profile.route) {
           ProfileScreen()
        }

        composable("recipe_screen") {
            RecipeScreen(localStorage = Storage(), lifecycle)
        }

    }

}

