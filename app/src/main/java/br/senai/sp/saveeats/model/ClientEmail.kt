package br.senai.sp.saveeats.model

data class ClientEmail(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val foto: String,
    val telefone: String
)
