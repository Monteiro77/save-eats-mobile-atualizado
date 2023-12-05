package br.senai.sp.saveeats.model

data class RestaurantRate(
    val avaliacao_id: Int,
    val nome_restaurante: String,
    val quantidade_estrela: Int,
    val avaliacao_descricao: String,
    val data_avaliacao: String,
    val recomendacao: String,
    val nome_cliente: String,
    val foto_cliente: String

)
