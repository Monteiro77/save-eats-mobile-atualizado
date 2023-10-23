package br.senai.sp.saveeats.model

data class CategoryList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val categorias: List<Category>
)