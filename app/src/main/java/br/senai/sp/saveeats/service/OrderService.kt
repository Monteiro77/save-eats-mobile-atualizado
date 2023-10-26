package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.Order
import br.senai.sp.saveeats.model.OrderInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderService {

    @GET("/v1/saveeats/detalhes/pedido/id/{id_pedido}")
    fun getOrderById(@Path("id_pedido") id_pedido: Int): Call<Order>
}