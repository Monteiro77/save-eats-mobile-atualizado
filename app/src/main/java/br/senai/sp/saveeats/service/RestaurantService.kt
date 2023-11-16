package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.AdressRestaurantList
import br.senai.sp.saveeats.model.HorarioDeFuncionamentoList
import br.senai.sp.saveeats.model.Restaurant
import br.senai.sp.saveeats.model.RestaurantList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantService {
    @GET("/v1/saveeats/restaurantes")
    fun getRestaurantCall(): Call<RestaurantList>

    @GET("/v1/saveeats/restaurantes")
    suspend fun getRestaurantResponse(): Response<RestaurantList>

    @GET("/v1/saveeats/endereco/restaurante/id/{id}")
    fun getAdressRestaurantByID(@Path("id") id: Int): Call<AdressRestaurantList>

    @GET("/v1/saveeats/restaurante/dia-horario-funcionamento/idRestaurante/{id}")
    fun getHorarioDeFuncionamentoByIdRestaurante(@Path("id") id: Int): Call<HorarioDeFuncionamentoList>



}