package com.example.ecokart.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ecokart.components.BottomNavigationBarSection
import com.example.ecokart.viewModel.ProductViewModel
import com.example.ecokart.R
import com.example.ecokart.viewModel.Product
import com.example.ecokart.viewModel.CartViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    product : Product,
    cartViewModel: CartViewModel
) {
    var selectedDelivery by remember { mutableStateOf("Green Delivery") }
    var selectedPackaging  by remember { mutableStateOf("Sustainable Packaging") }
    val context = LocalContext.current
    Scaffold(
        topBar =  {
            Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                Icon(
                    painter = painterResource(id = R.drawable.ecokart), // replace with your leaf icon
                    contentDescription = "EcoCart Logo",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("EcoKart", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Text("Eco Score: 85", fontSize = 14.sp, color = Color.Gray)
        }
        }
,
        bottomBar = { BottomNavigationBarSection(navController,cartViewModel) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Text(product.description, fontSize = 14.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Price: Rs${product.price}", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Eco Score:", fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(14.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(product.ecoScore / 100f)
                        .fillMaxHeight()
                        .background(Color(0xFF4CAF50)) // Green progress
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Delivery Options", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Column {
                listOf("Green Delivery", "Standard Delivery").forEach { option ->
                    DeliveryOptionCard(
                        text = option,
                        selected = selectedDelivery == option,
                        onClick = { selectedDelivery = option }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Packaging Options", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))
            Column {
                listOf("Sustainable Packaging", "Normal Packaging").forEach { option ->
                    DeliveryOptionCard(
                        text = option,
                        selected = selectedPackaging == option,
                        onClick = { selectedPackaging = option }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "product added to cart", Toast.LENGTH_SHORT).show()
                        cartViewModel.addToCart(product)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)

                ) {
                    Text("Add to Cart", color = Color.White)
                }

                Button(
                    onClick = {
                        cartViewModel.addToCart(product)
                        navController.navigate("checkout") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                    ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)

                ) {
                    Text("Buy Now", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(80.dp)) // bottom bar space
        }
    }
}

@Composable
fun DeliveryOptionCard(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) Color(0xFF4CAF50) else Color.LightGray)
            .padding(12.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color.Black,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

