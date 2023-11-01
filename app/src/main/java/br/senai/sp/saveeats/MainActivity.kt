package br.senai.sp.saveeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.categoryrestaurantcomponents.screen.CategoryRestaurantScreen
import br.senai.sp.saveeats.historiccomponents.DetalhesPedidoHistoricoScreen
import br.senai.sp.saveeats.logincomponents.screen.LoginScreen
import br.senai.sp.saveeats.menubarcomponents.screen.MenuScreen
import br.senai.sp.saveeats.ordercomponents.screen.OrderScreen
import br.senai.sp.saveeats.presentationcomponents.screen.FirstPresentationScreen
import br.senai.sp.saveeats.presentationcomponents.screen.SecondPresentationScreen
import br.senai.sp.saveeats.presentationcomponents.screen.ThirdPresentationScreen
import br.senai.sp.saveeats.productcomponents.screen.ProductScreen
import br.senai.sp.saveeats.productsrestaurantcomponents.screen.ProductsRestaurantScreen
import br.senai.sp.saveeats.profilecomponents.screen.ProfileScreen
import br.senai.sp.saveeats.profilerestaurantcomponents.screen.RestaurantProfile
import br.senai.sp.saveeats.recipecomponents.screen.RecipeScreen
import br.senai.sp.saveeats.shoppingcartcomponents.screen.ShoppingCartScreen
import br.senai.sp.saveeats.singupcomponents.screen.FirstSignup
import br.senai.sp.saveeats.singupcomponents.screen.SecondSignup
import br.senai.sp.saveeats.singupcomponents.screen.ThirdSignupScreen
import br.senai.sp.saveeats.splashcomponents.screen.SplashScreen
import br.senai.sp.saveeats.tipscomponents.screen.TipScreen
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import br.senai.sp.saveeats.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "profile_restaurant"
                ) {

                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }

                    composable("first_presentation_screen") {
                        FirstPresentationScreen(navController = navController)
                    }

                    composable("second_presentation_screen") {
                        SecondPresentationScreen(navController = navController)
                    }

                    composable("third_presentation_screen") {
                        ThirdPresentationScreen(navController = navController)
                    }

                    composable("first_signup_screen") {
                        FirstSignup(navController = navController, localStorage = Storage())
                    }

                    composable("second_signup_screen") {
                        SecondSignup(navController = navController, localStorage = Storage())
                    }

                    composable("third_signup_screen") {
                        ThirdSignupScreen(navController = navController, localStorage = Storage(), lifecycleScope = lifecycleScope)
                    }

                    composable("login_screen") {
                        LoginScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = Storage())
                    }

                    composable("home_screen") {
                        MenuScreen(navController2 = navController, lifecycle = lifecycleScope, viewModel = RestaurantViewModel())
                    }

                    composable("category_restaurant_screen") {
                        CategoryRestaurantScreen(localStorage = Storage(), navController = navController)
                    }

                    composable("shopping_cart_screen") {
                        ShoppingCartScreen(navController = navController ,localStorage = Storage())
                    }

                    composable("order_screen") {
                        OrderScreen(navController = navController, localStorage = Storage(), lifecycleScope = lifecycleScope, "", {})
                    }

                    composable("profile_screen") {
                        ProfileScreen()
                    }

                    composable("recipe_screen") {
                        RecipeScreen(localStorage = Storage())
                    }

                    composable("tip_screen") {
                        TipScreen(localStorage = Storage())
                    }

                    composable("products_restaurant_screen") {
                        ProductsRestaurantScreen(navController = navController, localStorage = Storage())
                    }

                    composable("products_screen") {
                        ProductScreen(navController = navController, localStorage = Storage())
                    }

                    composable("shopping_cart_screen") {
                        ShoppingCartScreen(navController = navController , localStorage = Storage())

                    }

                    composable("detalhes_pedido_historico_screen") {
                        DetalhesPedidoHistoricoScreen(navController = navController , localStorage = Storage())
                    }

                    composable("profile_restaurant") {
                        RestaurantProfile(navController = navController , localStorage = Storage())
                    }



                }

            }

        }

    }

}