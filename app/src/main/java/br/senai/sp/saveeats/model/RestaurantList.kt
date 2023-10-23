package br.senai.sp.saveeats.model

data class RestaurantList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val restaurantes: List<Restaurant>
)
