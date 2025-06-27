package com.example.ecokart.viewModel
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.example.ecokart.R
import kotlinx.parcelize.Parcelize
class ProductViewModel : ViewModel() {
    // You can use this method to load a product (demo for now)
    fun loadClothing() : List<Product> {
        return clothingProductList
    }
    fun loadTechnology(): List<Product>{
        return technologyProductList
    }
    fun loadEco() : List<Product> {
        return ecoFriendlyProductList
    }
    fun loadHomeAndOthers(): List<Product>{
        return homeAndOtherProductList
    }

}


@Parcelize
data class Product(
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val ecoScore: Int
    ): Parcelable

val clothingProductList = listOf(
    // 👕 Clothing
    Product(
        name = "Organic Cotton T-shirt",
        price = 18.99,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a3/T-shirt.png",
        description = "Soft, breathable, and made from 100% organic cotton. Perfect for everyday wear and sustainable fashion.",
        ecoScore = 80
    ),
    Product(
        name = "Denim Jacket",
        price = 45.50,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/14/Denim_jacket.png",
        description = "Classic denim jacket, long-lasting and ideal for layering. A timeless piece for any wardrobe.",
        ecoScore = 60
    ),
    Product(
        name = "Woolen Beanie Hat",
        price = 12.00,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b3/Beanie.png",
        description = "Warm and cozy wool beanie, great for cold winter days and a sustainable accessory for any outfit.",
        ecoScore = 75
    )
)

val technologyProductList = listOf(    // 💻 Technology
    Product(
        name = "Wireless Mouse",
        price = 25.99,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a2/Wireless_mouse.png",
        description = "Ergonomic and sleek wireless mouse for seamless productivity, ideal for long working hours.",
        ecoScore = 65
    ),
    Product(
        name = "Bluetooth Headphones",
        price = 59.99,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/9e/Bluetooth_Headphones.png",
        description = "High-quality sound with long battery life, making these headphones ideal for work or play.",
        ecoScore = 55
    ),
    Product(
        name = "Smartphone Stand",
        price = 14.50,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/12/Smartphone_Stand.png",
        description = "A simple, adjustable stand to hold your phone for video calls, reading, or watching movies.",
        ecoScore = 60
    )
)
val ecoFriendlyProductList =listOf(

    // ♻️ Eco-friendly
    Product(
        name = "Stainless Steel Bottle",
        price = 25.00,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/1c/Stainless-Steel-Bottle.png",
        description = "Durable and reusable bottle to reduce plastic waste. Perfect for hot or cold drinks.",
        ecoScore = 95
    ),
    Product(
        name = "Bamboo Toothbrush",
        price = 10.00,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a9/Bamboo-Toothbrush.png",
        description = "Eco-friendly bamboo toothbrush with soft bristles, ideal for a sustainable and healthy routine.",
        ecoScore = 90
    ),
    Product(
        name = "Cotton Tote Bag",
        price = 15.00,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b9/Cotton-tote.png",
        description = "Sturdy and stylish tote bag made from 100% cotton, ideal for shopping and daily use.",
        ecoScore = 85
    ),
    Product(
        name = "Handmade Soap Bar",
        price = 8.00,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/97/Handmade-soap.png",
        description = "Natural handmade soap bar for soft skin and a sustainable bathing experience.",
        ecoScore = 88
    )
)

val homeAndOtherProductList = listOf(

    // 🏠 Home & Other Items
    Product(
        name = "Ceramic Coffee Mug",
        price = 12.50,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/47/Ceramic-Coffee-Cup.png",
        description = "Sturdy ceramic mug for your morning brew. Simple, classic design for daily use.",
        ecoScore = 72
    ),
    Product(
        name = "Wooden Serving Bowl",
        price = 18.99,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a3/Wooden-bowl.png",
        description = "Rustic and beautifully handcrafted wooden bowl for salads, fruits, or decoration.",
        ecoScore = 78
    ),
    Product(
        name = "LED Desk Lamp",
        price = 34.99,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b3/LED-Desk-Lamp.png",
        description = "Modern LED lamp with adjustable brightness, ideal for work or reading environments.",
        ecoScore = 65
    )
)



