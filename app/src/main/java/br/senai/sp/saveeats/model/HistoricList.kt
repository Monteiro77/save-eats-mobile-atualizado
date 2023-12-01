package br.senai.sp.saveeats.model

data class HistoricList(
    val status : Int,
    val message : String,
    val detalhes_do_pedido_do_cliente : List<Historic>
)