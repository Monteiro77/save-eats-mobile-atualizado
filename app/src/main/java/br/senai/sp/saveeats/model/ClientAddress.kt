package br.senai.sp.saveeats.model

data class ClientAddress(
    val id_cliente: Int? = 0,
    val nome_cliente: String? = "",
    val logradouro_cliente: String? = "",
    val cep_cliente: String? = "",
    val bairro_cliente: String? = "",
    val numero_endereco_cliente: Int? = 0,
    val complemento_cliente: String? = "",
    val uf_cliente: String? = "",
    val localidade_cliente: String? = ""
)