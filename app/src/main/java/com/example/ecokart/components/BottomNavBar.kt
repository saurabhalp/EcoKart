package com.example.ecokart.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBarSection(navController: NavController) {
    // Maintain selected state
    val selectedItem = rememberSaveable { mutableStateOf("home") }

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedItem.value == "home",
            onClick = {
                selectedItem.value = "home"
                navController.navigate("home") {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Product") },
            label = { Text("Product") },
            selected = selectedItem.value == "productScreen",
            onClick = {
                selectedItem.value = "productScreen"
                navController.navigate("productScreen") {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Receipt, contentDescription = "Checkout") },
            label = { Text("Checkout") },
            selected = selectedItem.value == "checkout",
            onClick = {
                selectedItem.value = "checkout"
                // Add your navigation logic for the Checkout screen
                // navController.navigate("checkout")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Dashboard, contentDescription = "Dashboard") },
            label = {
                Text(
                    "Dashboard",
                    maxLines = 1,
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                )
            },
            selected = selectedItem.value == "dashboard",
            onClick = {
                selectedItem.value = "dashboard"
                navController.navigate("dashboard") {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
