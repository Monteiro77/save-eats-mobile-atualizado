package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class OrderRepository {

    private val service = RetrofitFactory
        .getOrder()

    suspend fun order(
        id_status_pedido: Int,
        id_restaurante_forma_pagamento: Int,
        id_restaurante_frete_area_entrega: Int,
        id_cliente: Int,
        id_restaurante: Int,
        produto_id1: Int,
        produto_id2: Int? = null
    ) : Response<JsonObject> {

        val requestBody = JsonObject().apply {

            addProperty("id_status_pedido", id_status_pedido)
            addProperty("id_restaurante_forma_pagamento", id_restaurante_forma_pagamento)
            addProperty("id_restaurante_frete_area_entrega", id_restaurante_frete_area_entrega)
            addProperty("id_cliente", id_cliente)
            addProperty("id_restaurante", id_restaurante)
            addProperty("produto_id1", produto_id1)
            addProperty("produto_id2", produto_id2)

        }

        return service.order(requestBody)

    }

}