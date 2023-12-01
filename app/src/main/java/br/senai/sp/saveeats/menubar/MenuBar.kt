package br.senai.sp.saveeats.menubar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MenuBar(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: MenuBar(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Orders: MenuBar(
        route = "orders",
        title = "Orders",
        icon = Icons.Outlined.ListAlt
    )
    object Recipes: MenuBar(
        route = "recipes",
        title = "Recipes",
        icon = Icons.Outlined.Book
    )
    object Profile: MenuBar(
        route = "profile",
        title = "Profile",
        icon = Icons.Outlined.Person
    )

}