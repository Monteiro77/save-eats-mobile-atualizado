package br.senai.sp.saveeats.model

data class DeliveryAreaRestaurantList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val frete_area_entrega_do_restaurante: DeliveryAreaRestaurant
)