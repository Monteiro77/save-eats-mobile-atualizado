package br.senai.sp.saveeats.model

data class Restaurant(
    val id: Int,
    val nome_proprietario: String,
    val nome_fantasia: String,
    val razao_social: String,
    val email: String,
    val senha: String,
    val foto: String,
    val cnpj: String,
    val id_categoria_restaurante: Int,
    val nome_categoria_restaurante: String,
    val id_endereco_restaurante: Int
)
