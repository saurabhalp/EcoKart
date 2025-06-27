package com.example.ecokart.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import coil.compose.AsyncImage
import com.example.ecokart.components.BottomNavigationBarSection
import com.example.ecokart.viewModel.ProductViewModel
import com.example.ecokart.R
import com.example.ecokart.viewModel.Product

@Composable
fun ProductDetailScreen(
    navController: NavController,
    product : Product
) {
    Scaffold(
        topBar =  {
            Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.White).padding(16.dp).statusBarsPadding(),
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
        bottomBar = { BottomNavigationBarSection(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding).systemBarsPadding()
                .verticalScroll(rememberScrollState()).padding(16.dp)
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

            Spacer(modifier = Modifier.height(2.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = false, onClick = { })
                    Text("Green Delivery")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = false, onClick = { })
                    Text("Standard Delivery")
                }

            }

            Text(text = "Packaging Options", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(2.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = false, onClick = { })
                    Text("Sustainable Packaging")
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    RadioButton(selected = false, onClick = { })
                    Text("Normal Packaging")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Handle cart */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)

                ) {
                    Text("Add to Cart", color = Color.White)
                }

                Button(
                    onClick = { /* Handle resale */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                            ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)

                ) {
                    Text("Select Resale", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(80.dp)) // bottom bar space
        }
    }
}


