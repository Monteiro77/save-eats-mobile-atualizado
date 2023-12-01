package br.senai.sp.saveeats.model

data class OrderList(
    val status: Int,
    val message: String,
    val detalhes_do_pedido: OrderInformation
)
