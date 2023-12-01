package br.senai.sp.saveeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.categoryrestaurant.screen.CategoryRestaurantScreen
import br.senai.sp.saveeats.editprofile.screen.EditProfileScreen
import br.senai.sp.saveeats.historic.screen.DetalhesPedidoHistoricoScreen
import br.senai.sp.saveeats.login.screen.LoginScreen
import br.senai.sp.saveeats.menubar.screen.MenuScreen
import br.senai.sp.saveeats.order.screen.OrderScreen
import br.senai.sp.saveeats.presentation.screen.FirstPresentationScreen
import br.senai.sp.saveeats.presentation.screen.SecondPresentationScreen
import br.senai.sp.saveeats.presentation.screen.ThirdPresentationScreen
import br.senai.sp.saveeats.product.screen.ProductScreen
import br.senai.sp.saveeats.productsrestaurant.screen.ProductsRestaurantScreen
import br.senai.sp.saveeats.profile.screen.ProfileScreen
import br.senai.sp.saveeats.recipe.screen.RecipeScreen
import br.senai.sp.saveeats.shoppingcart.screen.ShoppingCartScreen
import br.senai.sp.saveeats.singup.screen.FirstSignup
import br.senai.sp.saveeats.singup.screen.SecondSignup
import br.senai.sp.saveeats.singup.screen.ThirdSignupScreen
import br.senai.sp.saveeats.splash.screen.SplashScreen
import br.senai.sp.saveeats.tips.screen.TipScreen
import br.senai.sp.saveeats.trackorder.screen.TrackOrder
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import br.senai.sp.saveeats.viewmodel.RestaurantViewModel
import br.senai.sp.saveeats.waitingfororder.screen.WaitingForOrderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login_screen"
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
                        SecondSignup(navController = navController, localStorage = Storage(), lifecycleScope = lifecycleScope)
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
                        OrderScreen(navController = navController, localStorage = Storage(), lifecycleScope = lifecycleScope, "",  {})
                    }

                    composable("profile_screen") {
                        ProfileScreen(navController = navController, localStorage = Storage())
                    }

                    composable("edit_profile_screen") {
                        EditProfileScreen(localStorage = Storage())
                    }

                    composable("recipe_screen") {
                        RecipeScreen(navController = navController, localStorage = Storage())
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

                    composable("waiting_for_order_screen") {
                        WaitingForOrderScreen(navController = navController)
                    }

                    composable("track_order_screen") {
                        TrackOrder(navController = navController, localStorage = Storage())
                    }

                    composable("detalhes_pedido_historico_screen") {
                        DetalhesPedidoHistoricoScreen(navController = navController , localStorage = Storage())
                    }

                }

            }

        }

    }

}