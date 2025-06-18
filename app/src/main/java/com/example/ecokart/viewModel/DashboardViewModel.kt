package com.example.ecokart.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.ecokart.R

class EcoDashboardViewModel : ViewModel() {

    private val _ecoScore = MutableStateFlow(75)
    val ecoScore: StateFlow<Int> = _ecoScore

    private val _recentPurchases = MutableStateFlow(
        listOf(
            Purchase("Reusable Water Bottle", "2023-10-05", R.drawable.ecokart),
            Purchase("Organic Cotton T-Shirt", "2023-09-21", R.drawable.ecokart)
        )
    )
    val recentPurchases: StateFlow<List<Purchase>> = _recentPurchases

    private val _carbonSavings = MutableStateFlow(
        listOf(30f, 10f, 5f, 25f)
    )
    val carbonSavings: StateFlow<List<Float>> = _carbonSavings
}

data class Purchase(val name: String, val date: String, val imageRes: Int)
