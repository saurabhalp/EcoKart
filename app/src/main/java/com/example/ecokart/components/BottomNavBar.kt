package com.example.ecokart.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlin.math.max

@Composable
fun BottomNavigationBarSection(navController: NavController) {
    val s = rememberSaveable { mutableStateOf(true) }
    var dashboard = remember { mutableStateOf(false) }
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
        , modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = s.value,
            onClick = {
                navController.navigate(route = "home")
                dashboard.value = false
                s.value = true

            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Product") },
            label = { Text("Product") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Receipt, contentDescription = "Checkout") },
            label = { Text("Checkout") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AttachMoney, contentDescription = "Sell") },
            label = { Text("Sell") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Dashboard, contentDescription = "Dashboard") },
            label = { Text("Dashboard", maxLines = 1, modifier = Modifier.horizontalScroll(rememberScrollState())
            ) },
            selected = !s.value,
            onClick = {
                s.value = false
                dashboard.value = true
                navController.navigate(route = "dashboard")

            }
        )
    }
}
