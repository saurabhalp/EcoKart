package com.example.ecokart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PieChartDemo() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(Color.Gray, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text("Pie Chart", color = Color.White)
    }
}

@Composable
fun BarChartDemo(values: List<Float>) {
    Column {
        values.forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width((it * 3).dp)
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("${it.toInt()} units")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
