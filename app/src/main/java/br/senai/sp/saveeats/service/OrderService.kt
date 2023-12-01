package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.OrderList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderService {
    @Headers("Content-Type: application/json")
    @POST("v1/saveeats/cliente/pedido")
    suspend fun order(@Body body: JsonObject): Response<JsonObject>

    @GET("/v1/saveeats/detalhes/pedido/id/{id_pedido}")
    fun getOrderById(@Path("id_pedido") id_pedido: Int): Call<OrderList>
}