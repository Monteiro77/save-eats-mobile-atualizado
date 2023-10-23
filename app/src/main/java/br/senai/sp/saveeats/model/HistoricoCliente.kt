package br.senai.sp.saveeats.model

data class HistoricoCliente (
    val status : Int,
    val message : String,
    val detalhes_do_pedido_do_cliente : List<OrderList>
)