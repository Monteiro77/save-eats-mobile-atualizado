package br.senai.sp.saveeats.model

data class RecipesList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val receitas: List<Recipes>
)
