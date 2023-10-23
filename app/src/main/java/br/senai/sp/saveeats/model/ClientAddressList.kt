package br.senai.sp.saveeats.model

data class ClientAddressList(
    val status: Int,
    val message: String,
    val endereco_cliente: List<ClientAddress>
)
