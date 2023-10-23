package br.senai.sp.saveeats.model

data class RecipeIngredients(
    val id_ingrediente: Int,
    val nome_ingrediente: String,
    val quantidade: String,
    val foto_ingrediente: String
)
