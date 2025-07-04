package com.example.ecokart.components
import androidx.compose.material.BadgedBox
import androidx.compose.material.Badge
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecokart.viewModel.CartViewModel

@Composable
fun BottomNavigationBarSection(navController: NavController,cartViewModel: CartViewModel) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") {
                    navController.navigate("home") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo("home") { inclusive = false }
                    }
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Product") },
            label = { Text("Product") },
            selected = currentRoute == "productScreen",
            onClick = {
                if (currentRoute != "productScreen") {
                    navController.navigate("productScreen") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo("home") { inclusive = false }
                    }
                }
            }
        )
        BottomNavigationItem(
            icon = {
                BadgedBox(
                    badge = {
                        if (cartViewModel.cartItems.isNotEmpty()) {
                            Badge {
                                Text("${cartViewModel.cartItems.size}")
                            }
                        }
                    }
                ) {
                    Icon(Icons.Default.Receipt, contentDescription = "Checkout")
                }
            },
            label = { Text("Checkout") },
            selected = currentRoute == "checkout",
            onClick = {
                if (currentRoute != "checkout") {
                    navController.navigate("checkout") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo("home") { inclusive = false }
                    }
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Dashboard, contentDescription = "Dashboard") },
            label = { Text("Dashboard") },
            selected = currentRoute == "dashboard",
            onClick = {
                if (currentRoute != "dashboard") {
                    navController.navigate("dashboard") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo("home") { inclusive = false }
                    }
                }
            }
        )
    }
}
