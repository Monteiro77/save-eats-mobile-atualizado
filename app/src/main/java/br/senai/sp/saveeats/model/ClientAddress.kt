package br.senai.sp.saveeats.model

data class ClientAddress(
    val id_cliente: Int? = 0,
    val rua_cliente: String? = "",
    val cep_cliente: String? = "",
    val bairro_cliente: String? = "",
    val numero_endereco_cliente: Int? = 0,
    val complemento_cliente: String? = "",
    val nome_cidade: String? = "",
    val nome_estado: String? = ""
)
