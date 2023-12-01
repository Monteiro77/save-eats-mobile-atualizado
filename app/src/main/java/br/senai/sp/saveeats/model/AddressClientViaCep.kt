package br.senai.sp.saveeats.model

data class AddressClientViaCep(
    val cep: String = "",
    val logradouro: String = "",
    val complemento: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val uf: String = ""
)
