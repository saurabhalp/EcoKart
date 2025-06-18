package com.example.ecokart.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecokart.R

@Composable
fun EcoCartScreen(navController: NavController) {
        ProductListSection(navController)
}

@Composable
fun ProductListSection(navController: NavController) {
    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            Text("Eco-Friendly Products", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* filter action */ }) {
                Text("Filter")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            itemsIndexed(items = productList.chunked(2)) { index, rowItems ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (product in rowItems) {
                        ProductCard(
                            product, Modifier.weight(1f),
                            navController = navController
                        )

                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f)) // fill space for missing 2nd column
                    }
                }
            }
            item {
                Card(
                    backgroundColor = Color(0xFFE0E0E0),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Special Promotion", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Get 20% off on all bamboo products!", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product,modifier: Modifier,navController: NavController) {
    Card(Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .background(Color.White).padding()
                .clickable(
                    onClick = {navController.navigate(route = "productDetail")}
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(product.name, fontWeight = FontWeight.SemiBold)
            Text("$${product.price}", color = Color.Gray)
            Spacer(Modifier.height(10.dp))
        }
    }
}


data class Product(val name: String, val price: Double, val imageRes: Int )

val productList = listOf(
    Product("Stainless Steel Bottle", 25.0, R.drawable.ecokart),
    Product("Bamboo Toothbrushes", 10.0, R.drawable.ecokart),
    Product("Cotton Tote Bag", 15.0, R.drawable.ecokart),
    Product("Handmade Soap Bars", 8.0, R.drawable.ecokart)
)

@Preview
@Composable
fun product(){
    ProductListSection(navController = rememberNavController())
}
