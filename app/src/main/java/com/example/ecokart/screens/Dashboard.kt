package com.example.ecokart.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecokart.viewModel.EcoDashboardViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun EcoDashboardScreen(viewModel: EcoDashboardViewModel = viewModel(),navController: NavController) {
    val ecoScore by viewModel.ecoScore.collectAsState()
    val recentPurchases by viewModel.recentPurchases.collectAsState()
    val carbonSavings by viewModel.carbonSavings.collectAsState()
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Eco Score Section
            CardSection(title = "Your Eco-Score") {
                Text("Current Score: $ecoScore/100", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                PieChartDemo() // Replace with real chart later
            }

            // Recent Purchases Section
            CardSection(title = "Recent Purchases") {
                recentPurchases.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = it.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(it.name, fontWeight = FontWeight.Bold)
                            Text("Purchased on: ${it.date}", fontSize = 12.sp)
                        }
                    }
                }
            }

            // Carbon Savings Section
            CardSection(title = "Carbon Savings") {
                BarChartDemo(carbonSavings)
            }

            // Explore Options
            CardSection(title = "Explore Eco-Friendly Options") {
                Column {
                    Button(
                        onClick = { Toast.makeText(context, "Buy eco friendly products for eco friendly life", Toast.LENGTH_LONG).show() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text("Shop Sustainable Products")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { navController.navigate("productScreen")  },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text("Learn More About Eco Living")
                    }
                }
            }
        }
    }
}

@Composable
fun CardSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FFF0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}
