package br.senai.sp.saveeats.model

data class CategoryTipsList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val categorias: List<CategoryTips>
)
