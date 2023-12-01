package br.senai.sp.saveeats.model

data class CategoryRestaurantList(
    val status: Int? = 404,
    val message: String,
    val quantidade: Int,
    val restaurantes_da_categoria_escolhida: List<CategoryRestaurant>
)
