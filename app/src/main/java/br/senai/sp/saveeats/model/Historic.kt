package br.senai.sp.saveeats.model

data class Historic(
    val id_pedido: Int,
    val cep_cliente: String,
    val bairro_cliente: String,
    val complemento_cliente: String,
    val nome_restaurante: String,
    val foto_restaurante: String,
    val numero_pedido: String,
    val horario_pedido: String,
    val data_pedido: String,
    val previsao_entrega: String,
    val valor_total: String,
    val status_pedido: String,
    val nome_forma_pagamento: String,
    val km: String,
    val valor_entrega: String,
    val tempo_entrega: String
)
