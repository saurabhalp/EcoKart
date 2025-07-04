package com.example.ecokart.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(product: Product) {
        val index = _cartItems.indexOfFirst { it.product.name == product.name }
        if (index != -1) {
            _cartItems[index] = _cartItems[index].copy(quantity = _cartItems[index].quantity + 1)
        } else {
            _cartItems.add(CartItem(product))
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }
}

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)
