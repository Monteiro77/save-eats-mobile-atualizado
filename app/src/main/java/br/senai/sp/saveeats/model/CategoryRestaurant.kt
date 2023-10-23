package br.senai.sp.saveeats.model

data class CategoryRestaurant(
    val id: Int,
    val nome_proprietario: String,
    val nome_fantasia: String,
    val razao_social: String,
    val email: String,
    val senha: String,
    val foto: String,
    val id_categoria_restaurante: Int,
    val id_endereco_restaurante: Int,
    val cnpj: String
)
