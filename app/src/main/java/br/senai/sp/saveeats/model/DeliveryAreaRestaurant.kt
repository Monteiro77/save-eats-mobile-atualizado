package br.senai.sp.saveeats.model

data class DeliveryAreaRestaurant(
    val restaurante_id: Int? = 0,
    val area_entrega_id: Int? = 0,
    val km: String? = "",
    val valor_entrega: Float? = 0.0f,
    val tempo_entrega: String? = "",
    val raio_entrega: Int? = 0,
)