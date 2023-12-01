package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.DeliveryAreaRestaurantList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeliveryAreaService {
    @GET("/v1/saveeats/restaurante/frete-area-entrega2/idRestaurante/{id_restaurant}")
    fun getDeliveryArea(@Path("id_restaurant") id_restaurant: Int): Call<DeliveryAreaRestaurantList>

}