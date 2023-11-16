package br.senai.sp.saveeats.model

data class OrderInformation(
    val nome_restaurante: String,
    val numero_pedido: String,
    val horario_pedido: String,
    val previsao_entrega: String,
    val data_pedido: String,
    val valor_total: String,
    val status_pedido: String,
    val nome_forma_pagamento: String,
    val valor_entrega: String,
    val tempo_entrega: String,
    val produtos: List<ProductOrderList>
)
