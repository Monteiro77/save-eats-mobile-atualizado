package br.senai.sp.saveeats.model

data class TipsList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val dica: List<Tips>
)
