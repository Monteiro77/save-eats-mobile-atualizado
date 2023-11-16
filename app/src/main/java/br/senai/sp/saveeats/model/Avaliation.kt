package br.senai.sp.saveeats.model

data class Avaliation(
    val quantidade_estrela: Float,
    val avaliacao_descricao: String,
    val data_avaliacao: String,
    val recomendacao: String,
    val nome_cliente: String,
    val foto_cliente: String
)
