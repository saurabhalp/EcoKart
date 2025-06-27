package com.example.ecokart.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecokart.viewModel.ProductViewModel

@Composable
fun ProductScreen(productViewModel: ProductViewModel, navController: NavController) {
    val techProduct = productViewModel.loadTechnology()
    val clothing = productViewModel.loadClothing()
    val eco = productViewModel.loadEco()
    val home = productViewModel.loadHomeAndOthers()

    LazyColumn {
        item {
            Text(text = "Tech Products", modifier = Modifier.padding(16.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            LazyRow {
                items(techProduct.size) { it->
                    ProductCard(
                        product = techProduct[it],
                        navController = navController,
                    )
                }
            }
        }
        item {
            Text(text = "Clothing Products", modifier = Modifier.padding(16.dp),fontSize = 24.sp, fontWeight = FontWeight.Bold)
            LazyRow {
                items(clothing.size) { it ->
                    ProductCard(
                        product = clothing[it],
                        navController = navController,

                    )
                }
            }
        }
        item {
            Text(text = "Eco-Friendly Products", modifier = Modifier.padding(16.dp),fontSize = 24.sp, fontWeight = FontWeight.Bold)
            LazyRow {
                items(eco.size) { it ->
                    ProductCard(
                        product = eco[it],
                        navController = navController,

                    )
                }
            }
        }
        item {
            Text(text = "Home & Others", modifier = Modifier.padding(16.dp),fontSize = 24.sp, fontWeight = FontWeight.Bold)
            LazyRow {
                items(home.size) { it ->
                    ProductCard(
                        product = home[it],
                        navController = navController,

                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview(){
    val p = ProductViewModel()
    ProductScreen(
        productViewModel  = p,
        navController = rememberNavController()
    )
}