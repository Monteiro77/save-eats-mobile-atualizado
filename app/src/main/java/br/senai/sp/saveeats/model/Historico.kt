package br.senai.sp.saveeats.model

data class Historico(
    val id_pedido: Int,
    val rua_cliente: String,
    val cep_cliente: String,
    val bairro_cliente: String,
    val numero_cliente: Int,
    val cidade_cliente: String,
    val estado_cliente: String,
    val nome_restaurante: String,
    val foto_restaurante: String,
    val numero_pedido: String,
    val horario_pedido: String,
    val data_pedido: String,
    val previsao_entrega: String,
    val valor_total: Float,
    val status_pedido: String,
    val id_restaurante_forma_pagamento: Int,
    val id_forma_pagamento: Int,
    val nome_forma_pagamento: String,
    val valor_entrega: Float,
    val tempo_entrega: String
)
