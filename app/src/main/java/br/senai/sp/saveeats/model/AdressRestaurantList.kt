package br.senai.sp.saveeats.model

data class AdressRestaurantList(
    val status: String,
    val message: String,
    val endereco_restaurante: AdressRestaurant
)
