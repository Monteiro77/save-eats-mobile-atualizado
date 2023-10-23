package br.senai.sp.saveeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.logincomponents.screen.LoginScreen
import br.senai.sp.saveeats.menubarcomponents.screen.MenuScreen
import br.senai.sp.saveeats.ordercomponents.screen.OrderScreen
import br.senai.sp.saveeats.presentationcomponents.screen.FirstPresentationScreen
import br.senai.sp.saveeats.presentationcomponents.screen.SecondPresentationScreen
import br.senai.sp.saveeats.presentationcomponents.screen.ThirdPresentationScreen
import br.senai.sp.saveeats.recipecomponents.screen.RecipeScreen
import br.senai.sp.saveeats.singupcomponents.screen.FirstSignup
import br.senai.sp.saveeats.singupcomponents.screen.SecondSignup
import br.senai.sp.saveeats.singupcomponents.screen.ThirdSignupScreen
import br.senai.sp.saveeats.splashcomponents.screen.SplashScreen
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import br.senai.sp.saveeats.viewmodel.LoginScreenViewModel
import br.senai.sp.saveeats.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "order_screen"
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
                        LoginScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = Storage(), viewModel = LoginScreenViewModel())
                    }

                    composable("home_screen") {
                        MenuScreen(navController2 = navController, lifecycle = lifecycleScope, viewModel = RestaurantViewModel())
                    }

                    composable("recipe_screen") {
                        RecipeScreen(localStorage = Storage(), lifecycleScope)
                    }
                    composable("order_screen") {
                        OrderScreen(localStorage = Storage(), navController = navController, navController2 = navController)
                    }

                }

            }

        }

    }

}