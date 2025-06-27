package com.example.ecokart.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ecokart.viewModel.Product
import com.example.ecokart.viewModel.ProductViewModel

@Composable
fun EcoCartScreen(navController: NavController, productViewModel: ProductViewModel) {
    val ecoProducts = productViewModel.loadEco()
    ProductListSection(productList = ecoProducts, navController = navController)
}

// Main List Section
@Composable
fun ProductListSection(productList: List<Product>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(top = 4.dp)
    ) {
        item {
            HomeQuote()
        }

        item {
            SectionHeader(title = "Eco-Friendly Products") {
                LazyRow {
                    items(productList.size) { index ->
                        ProductCard(
                            product = productList[index],
                            navController = navController
                        )
                    }
                }
            }
        }

        item {
            SpecialPromotion(
                title = "Special Promotion",
                message = "Get 20% off on all bamboo products!"
            )
        }

        item {
            HomePromotions()
        }
    }
}

// Product Item
@Composable
fun ProductCard(product: Product, navController: NavController) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .background(Color.White)
                .padding()
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("selectedProduct", product)
                    navController.navigate("productDetail")
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(Modifier.height(4.dp))
            Text(product.name, fontWeight = FontWeight.SemiBold)
            Text("₹${product.price}", color = Color.Gray)
            Spacer(Modifier.height(10.dp))
        }
    }
}

// Quote
@Composable
fun HomeQuote() {
    val quotes = listOf(
        "🌱 Small choices, big impact. Shop eco-friendly today.",
        "🛍️ Sustainability starts with a single purchase.",
        "🌍 Shop smart. Live green. Feel good.",
        "💚 EcoKart: Your partner in conscious living.",
        "🌳 Every product, a promise to the planet."
    )
    val selectedQuote = quotes.random()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0F7FA))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = selectedQuote,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00796B),
            textAlign = TextAlign.Center
        )
    }
}

// Section Header + Content
@Composable
fun SectionHeader(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        content()
    }
}

// Single Promotion
@Composable
fun SpecialPromotion(title: String, message: String) {
    Card(
        backgroundColor = Color(0xFFE0E0E0),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(message, fontSize = 18.sp)
        }
    }
}

// Random Home Promotions
@Composable
fun HomePromotions() {
    val promotions = listOf(
        "💥 Limited Time: Flat 10% Off All Eco-Friendly Products!",
        "🎁 Buy 2 Bamboo Toothbrushes, Get 1 FREE!",
        "🚚 Free Shipping on Orders Above ₹499",
        "👕 New Arrival: Organic Cotton Tees from ₹299",
        "💻 Tech Meets Green: Extra 5% Off Eco-Friendly Gadgets"
    )
    val selectedPromotion = promotions.random()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0F7FA))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = selectedPromotion,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF004D40),
            textAlign = TextAlign.Center
        )
    }
}

// PREVIEW
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductListSectionPreview() {
    val sampleProducts = ProductViewModel().loadEco()
    ProductListSection(productList = sampleProducts, navController = rememberNavController())
}
