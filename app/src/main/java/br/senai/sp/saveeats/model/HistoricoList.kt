package br.senai.sp.saveeats.model

data class OrderList(
    val id_pedido : Int? = 0,
    val rua_cliente : String? = "",
    val cep_cliente : String? = "",
    val bairro_cliente : String? = "",
    val numero_cliente : Int? = 0,
    val cidade_cliente : String? = "",
    val estado_cliente : String? = "",
    val nome_restaurante : String? = "",
    val foto_restaurante : String,
    val numero_pedido : String? = "",
    val horario_pedido : String? = "",
    val data_pedido : String? = "",
    val previsao_entrega : String? = "",
    val valor_total : Int? = 0,
    val status_pedido : String? = "",
    val id_restaurante_forma_pagamento : Int? = 0,
    val id_forma_pagamento : Int? = 0 ,
    val nome_forma_pagamento : String? = "",
    val valor_entrega : Int? = 0,
    val tempo_entrega : String? = "",
    val produtos : List<ProductHistoricoList>? = emptyList()


)
