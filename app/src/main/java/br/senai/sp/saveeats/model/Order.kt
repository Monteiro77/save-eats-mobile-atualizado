package br.senai.sp.saveeats.model

data class Order(
    val status: Int,
    val message: String,
    val detalhes_do_pedido: OrderInformation
)
