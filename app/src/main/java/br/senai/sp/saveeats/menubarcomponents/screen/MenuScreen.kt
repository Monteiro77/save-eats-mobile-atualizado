package br.senai.sp.saveeats.menubarcomponents.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(navController2: NavHostController, lifecycle: LifecycleCoroutineScope, viewModel: ViewModel) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {

        BottomNavGraph(navController = navController, navController2, lifecycle = lifecycle, viewModel = viewModel)

    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        MenuBar.Home,
        MenuBar.Orders,
        MenuBar.Recipes,
        MenuBar.Profile
    )

    val  navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier
            .height(50.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {

        screens.forEach { screen ->

            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController

            )
        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: MenuBar,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val currentRoute = currentDestination?.route

    val selected = currentRoute == screen.route

    BottomNavigationItem(
        icon = {

            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = screen.icon,
                contentDescription = "",
                tint = if(selected) colorResource(id = R.color.green_save_eats_light)else colorResource(id = R.color.gray)
            )

        },

        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,

        onClick = {
            navController.navigate(screen.route)
        },

        selectedContentColor = colorResource(id = R.color.slide_three)

    )

}