package br.senai.sp.saveeats.model

data class AddressRestaurant(
    val rua : String,
    val cep : String,
    val bairro: String,
    val numero: String,
    val complemento: String

)