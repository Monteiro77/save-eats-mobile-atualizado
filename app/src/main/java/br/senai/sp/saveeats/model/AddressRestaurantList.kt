package br.senai.sp.saveeats.model

data class AddressRestaurantList(
    val status: String,
    val message: String,
    val endereco_restaurante: List<AddressRestaurant>
)
