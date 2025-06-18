package com.example.ecokart.viewModel
import androidx.lifecycle.ViewModel
import com.example.ecokart.R

class ProductViewModel : ViewModel() {

    // You can use this method to load a product (demo for now)
    fun loadDemoProduct() : Product {
        val selectedProduct = Product(
            name = "Eco-friendly Bamboo Toothbrush",
            price = 4.99,
            imageRes = R.drawable.ecokart, // replace with actual bamboo image if you have
            description = "Crafted from sustainably sourced bamboo, this toothbrush is a perfect addition to your eco–friendly lifestyle. The soft bristles are designed for effective cleaning while being gentle on your gums.",
            ecoScore = 85
        )
        return selectedProduct
    }
}

data class Product(
    val name: String,
    val price: Double,
    val imageRes: Int,
    val description: String,
    val ecoScore: Int
    )



