package br.senai.sp.saveeats.model

data class CategoryRecipesList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val categorias: List<CategoryRecipes>
)
