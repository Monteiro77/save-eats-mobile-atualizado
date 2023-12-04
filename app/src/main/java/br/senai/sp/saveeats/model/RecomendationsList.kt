package br.senai.sp.saveeats.model

data class RecomendationsList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val recomendacoes: List<Recomendation>
)
