package com.example.ecokart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecokart.components.BottomNavigationBarSection
import com.example.ecokart.components.TopAppBarSection
import com.example.ecokart.screens.CheckoutScreen
import com.example.ecokart.screens.EcoCartScreen
import com.example.ecokart.screens.EcoDashboardScreen
import com.example.ecokart.screens.ProductDetailScreen
import com.example.ecokart.screens.ProductScreen
import com.example.ecokart.screens.SplashScreen
import com.example.ecokart.ui.theme.EcoKartTheme
import com.example.ecokart.viewModel.CartViewModel
import com.example.ecokart.viewModel.EcoDashboardViewModel
import com.example.ecokart.viewModel.Product
import com.example.ecokart.viewModel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoKartTheme {
                val navController = rememberNavController()
                val productViewModel: ProductViewModel = viewModel()
                val dashboardViewModel: EcoDashboardViewModel = viewModel()
                val cartViewModel: CartViewModel = viewModel()
                    EcoKartApp(
                        modifier = Modifier,
                        productViewModel = productViewModel,
                        dashboardViewModel = dashboardViewModel,
                        cartViewModel = cartViewModel
                    )
            }
        }
    }
}
@Composable
fun EcoKartApp(modifier: Modifier = Modifier,
               productViewModel: ProductViewModel,
               dashboardViewModel: EcoDashboardViewModel,
               cartViewModel: CartViewModel
               ) {
    val navController = rememberNavController()

    // Watch current route
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route


    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        composable("splash") {
            SplashScreen(
                modifier = Modifier,
                navController = navController
            )
        }

        composable("home") {
            Scaffold(
                topBar = { TopAppBarSection() },
                bottomBar = { BottomNavigationBarSection(navController,cartViewModel) }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    EcoCartScreen(
                        navController,
                        productViewModel
                    )
                }
            }
        }
        composable("productScreen") {
            Scaffold(
                topBar = { TopAppBarSection() },
                bottomBar = { BottomNavigationBarSection(navController,cartViewModel) }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    ProductScreen(
                        productViewModel,
                        navController = navController,
                    )
                }
            }
        }
        composable("dashboard") {
            Scaffold(
                topBar = { TopAppBarSection() },
                bottomBar = { BottomNavigationBarSection(navController,cartViewModel) }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .systemBarsPadding()
                ) {
                    EcoDashboardScreen(dashboardViewModel,navController)
                }
            }
        }

        composable("productDetail") {
            val selectedProduct = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Product>("selectedProduct")

            selectedProduct?.let {
                ProductDetailScreen(
                    navController = navController,
                    product = it,
                    cartViewModel = cartViewModel
                )
            }
        }

        composable("checkout"){
            Scaffold(
                topBar = { TopAppBarSection() },
                bottomBar = { BottomNavigationBarSection(navController,cartViewModel) }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .systemBarsPadding()
                ) {
                    CheckoutScreen(
                        navController = navController,
                        cartViewModel = cartViewModel
                    )
                }
            }
        }

    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcoKartTheme {
        Greeting("Android")
    }
}