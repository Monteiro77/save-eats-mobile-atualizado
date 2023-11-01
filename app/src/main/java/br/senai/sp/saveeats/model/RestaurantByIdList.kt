package br.senai.sp.saveeats.model

data class RestaurantByIdList(
    val status: Int,
    val message: String,
    val restaurantes: List<RestaurantById>
)
