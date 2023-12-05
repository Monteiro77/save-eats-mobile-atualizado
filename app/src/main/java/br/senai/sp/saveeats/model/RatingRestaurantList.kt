package br.senai.sp.saveeats.model

data class RatingRestaurantList(
    val status:Int,
    val message: String,
    val quantidade_avaliacoes: Int,
    val media_estrelas: String,
    val avaliacoes_do_restaurante: List<RestaurantRate>
)
